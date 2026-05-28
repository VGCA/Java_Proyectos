package com.bosonit.uploadspring;

import com.bosonit.uploadspring.controlador.Controlador;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

@WebMvcTest(Controlador.class)
class ControladorTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("GET /index → devuelve vista 'uploader' con HTTP 200")
    void mostrarIndex_debeRetornarVistaUploader() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("uploader"));
    }


    @Test
    @DisplayName("POST /subir con fichero vacío → 200 o 500 son válidos")
    void uploadFile_conFicheroVacio_debeRetornar200OError() throws Exception {
        MockMultipartFile ficheroVacio = new MockMultipartFile(
                "file",
                "",
                MediaType.TEXT_PLAIN_VALUE,
                new byte[0]
        );

        int status = mockMvc.perform(multipart("/subir").file(ficheroVacio))
                .andReturn()
                .getResponse()
                .getStatus();

        assertThat(status).isIn(200, 500);
    }

    @Test
    @DisplayName("POST /subir sin parámetro 'file' → HTTP 400 Bad Request")
    void uploadFile_sinParametroFile_debeRetornar400() throws Exception {
        mockMvc.perform(post("/subir")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /subir cuando transferTo lanza excepción → HTTP 500")
    void uploadFile_cuandoTransferFalla_debeRetornar500() throws Exception {

        MultipartFile ficheroMock = mock(MultipartFile.class);
        given(ficheroMock.getOriginalFilename()).willReturn("test.txt");
        doThrow(new IOException("Disco lleno"))
                .when(ficheroMock).transferTo(any(File.class));

        Controlador controlador = new Controlador();
        ResponseEntity<String> respuesta = controlador.uploadFile(ficheroMock);

        assertThat(respuesta.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
