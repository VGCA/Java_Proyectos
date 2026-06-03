package com.batch;

import com.batch.controller.BatchController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BatchController.class)
class BatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobLauncher jobLauncher;

    @MockBean
    private Job job;

    private MockMultipartFile validFile;

    @BeforeEach
    void setUp() {

        validFile = new MockMultipartFile(
                "file",
                "test-data.csv",
                MediaType.TEXT_PLAIN_VALUE,
                "col1,col2\nval1,val2".getBytes()
        );
    }


    @Test
    @DisplayName("POST /v1/uploadFile → 200 OK con nombre de archivo en respuesta")
    void uploadFile_validFile_returns200WithFileName() throws Exception {

        JobExecution mockExecution = mock(JobExecution.class);
        when(jobLauncher.run(eq(job), any(JobParameters.class)))
                .thenReturn(mockExecution);


        mockMvc.perform(multipart("/v1/uploadFile").file(validFile))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.archivo").value("test-data.csv"));
    }

    @Test
    @DisplayName("El jobLauncher se invoca exactamente una vez con parámetros correctos")
    void uploadFile_validFile_launchesJobOnce() throws Exception {

        when(jobLauncher.run(eq(job), any(JobParameters.class)))
                .thenReturn(mock(JobExecution.class));


        mockMvc.perform(multipart("/v1/uploadFile").file(validFile))
                .andExpect(status().isOk());


        verify(jobLauncher, times(1)).run(eq(job), any(JobParameters.class));
    }

    @Test
    @DisplayName("Los JobParameters incluyen 'nombre' con el nombre del archivo")
    void uploadFile_validFile_jobParametersContainFileName() throws Exception {

        when(jobLauncher.run(eq(job), any(JobParameters.class)))
                .thenAnswer(invocation -> {
                    JobParameters params = invocation.getArgument(1);

                    assert params.getString("nombre").equals("test-data.csv")
                            : "El parámetro 'nombre' debe contener el nombre del archivo";
                    assert params.getDate("fecha") != null
                            : "El parámetro 'fecha' no debe ser null";
                    return mock(JobExecution.class);
                });

        mockMvc.perform(multipart("/v1/uploadFile").file(validFile))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("Archivo con nombre que contiene espacios se procesa correctamente")
    void uploadFile_fileWithSpacesInName_returns200() throws Exception {
        MockMultipartFile fileWithSpaces = new MockMultipartFile(
                "file", "my file data.csv",
                MediaType.TEXT_PLAIN_VALUE, "data".getBytes()
        );
        when(jobLauncher.run(eq(job), any(JobParameters.class)))
                .thenReturn(mock(JobExecution.class));

        mockMvc.perform(multipart("/v1/uploadFile").file(fileWithSpaces))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.archivo").value("my file data.csv"));
    }

    @Test
    @DisplayName("Archivo vacío (0 bytes) no impide el lanzamiento del job")
    void uploadFile_emptyFile_stillLaunchesJob() throws Exception {
        MockMultipartFile emptyFile = new MockMultipartFile(
                "file", "empty.csv",
                MediaType.TEXT_PLAIN_VALUE, new byte[0]
        );
        when(jobLauncher.run(eq(job), any(JobParameters.class)))
                .thenReturn(mock(JobExecution.class));

        mockMvc.perform(multipart("/v1/uploadFile").file(emptyFile))
                .andExpect(status().isOk());

        verify(jobLauncher, times(1)).run(any(), any());
    }


    @Test
    @DisplayName("Si jobLauncher lanza excepción → el endpoint propaga RuntimeException (500)")
    void uploadFile_jobLauncherThrows_returns500() throws Exception {

        when(jobLauncher.run(eq(job), any(JobParameters.class)))
                .thenThrow(new Exception("Batch infrastructure failure"));


        mockMvc.perform(multipart("/v1/uploadFile").file(validFile))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Petición sin parámetro 'file' → 400 Bad Request")
    void uploadFile_missingFileParam_returns400() throws Exception {

        mockMvc.perform(multipart("/v1/uploadFile"))
                .andExpect(status().isBadRequest());


        verifyNoInteractions(jobLauncher);
    }

    @Test
    @DisplayName("El job NO se lanza si el jobLauncher ya falló antes de run()")
    void uploadFile_jobLauncherThrows_jobIsNotLaunchedSuccessfully() throws Exception {

        when(jobLauncher.run(any(), any()))
                .thenThrow(new Exception("Connection refused"));


        mockMvc.perform(multipart("/v1/uploadFile").file(validFile))
                .andExpect(status().isInternalServerError());


        verify(jobLauncher, times(1)).run(any(), any());
    }


    @org.junit.jupiter.api.AfterEach
    void cleanUp() throws Exception {

        Path testFile = Paths.get("src/main/resources/files/test-data.csv");
        Files.deleteIfExists(testFile);

        Path fileWithSpaces = Paths.get("src/main/resources/files/my file data.csv");
        Files.deleteIfExists(fileWithSpaces);

        Path emptyFile = Paths.get("src/main/resources/files/empty.csv");
        Files.deleteIfExists(emptyFile);
    }
}