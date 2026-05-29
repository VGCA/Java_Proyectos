package com.bosonit.csvh2;

import com.bosonit.csvh2.ayuda.CSVHelper;
import com.bosonit.csvh2.controlador.CSVController;
import com.bosonit.csvh2.mensaje.ResponseMessage;
import com.bosonit.csvh2.modelo.Tutorial;
import com.bosonit.csvh2.servicio.CSVService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CSVControllerTest {

    @Mock
    private CSVService fileService;

    @InjectMocks
    private CSVController csvController;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("uploadFile: archivo CSV válido → 200 OK con mensaje de éxito")
    void uploadFile_validCsv_returns200() throws Exception {

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "tutorials.csv",
                "text/csv",
                "id,title\n1,Java".getBytes()
        );


        try (MockedStatic<CSVHelper> csvHelper = mockStatic(CSVHelper.class)) {
            csvHelper.when(() -> CSVHelper.hasCSVFormat(file)).thenReturn(true);


            doNothing().when(fileService).save(file);


            ResponseEntity<ResponseMessage> response = csvController.uploadFile(file);


            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody().getMessage())
                    .contains("tutorials.csv");

            verify(fileService, times(1)).save(file);
        }
    }

    @Test
    @DisplayName("uploadFile: servicio lanza excepción → 417 EXPECTATION_FAILED")
    void uploadFile_serviceThrows_returns417() throws Exception {

        MockMultipartFile file = new MockMultipartFile(
                "file", "tutorials.csv", "text/csv",
                "id,title\n1,Java".getBytes()
        );

        try (MockedStatic<CSVHelper> csvHelper = mockStatic(CSVHelper.class)) {
            csvHelper.when(() -> CSVHelper.hasCSVFormat(file)).thenReturn(true);


            doThrow(new RuntimeException("DB error")).when(fileService).save(file);


            ResponseEntity<ResponseMessage> response = csvController.uploadFile(file);

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.EXPECTATION_FAILED);
            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody().getMessage())
                    .contains("Could not upload the file");
        }
    }

    @Test
    @DisplayName("uploadFile: archivo no es CSV → 400 BAD_REQUEST")
    void uploadFile_notCsv_returns400() {

        MockMultipartFile file = new MockMultipartFile(
                "file", "document.txt", "text/plain",
                "some text".getBytes()
        );

        try (MockedStatic<CSVHelper> csvHelper = mockStatic(CSVHelper.class)) {
            csvHelper.when(() -> CSVHelper.hasCSVFormat(file)).thenReturn(false);


            ResponseEntity<ResponseMessage> response = csvController.uploadFile(file);

            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
            assertThat(response.getBody()).isNotNull();
            assertThat(response.getBody().getMessage())
                    .isEqualTo("Please upload a csv file!");

            verifyNoInteractions(fileService);
        }
    }


    @Test
    @DisplayName("getAllTutorials: hay tutoriales → 200 OK con lista")
    void getAllTutorials_hasTutorials_returns200() {

        List<Tutorial> tutorials = Arrays.asList(
                new Tutorial(1,"Spring Boot", "Intro", true),
                new Tutorial(2,"JPA", "ORM", false)
        );
        when(fileService.getAllTutorials()).thenReturn(tutorials);

        ResponseEntity<List<Tutorial>> response = csvController.getAllTutorials();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
        assertThat(response.getBody().get(0).getTitle()).isEqualTo("Spring Boot");
    }

    @Test
    @DisplayName("getAllTutorials: lista vacía → 204 NO_CONTENT")
    void getAllTutorials_empty_returns204() {

        when(fileService.getAllTutorials()).thenReturn(Collections.emptyList());

        ResponseEntity<List<Tutorial>> response = csvController.getAllTutorials();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

    @Test
    @DisplayName("getAllTutorials: servicio lanza excepción → 500 INTERNAL_SERVER_ERROR")
    void getAllTutorials_serviceThrows_returns500() {

        when(fileService.getAllTutorials())
                .thenThrow(new RuntimeException("Unexpected DB failure"));

        ResponseEntity<List<Tutorial>> response = csvController.getAllTutorials();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNull();
    }
}
