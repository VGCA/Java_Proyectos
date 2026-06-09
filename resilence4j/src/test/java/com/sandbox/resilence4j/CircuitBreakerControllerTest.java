package com.sandbox.resilence4j;

import com.sandbox.resilence4j.controller.CircuitBreakerController;
import com.sandbox.resilence4j.exceptions.ServiceUnavailableException;
import com.sandbox.resilence4j.service.SampleService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CircuitBreakerController.class)
class CircuitBreakerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SampleService sampleService;


    @Test
    void shouldReturn200WhenServiceSucceeds() throws Exception {
        given(sampleService.callExternalService())
                .willReturn("Service call succeeded");

        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("Service call succeeded"));
    }


    @Test
    void shouldInvokeServiceExactlyOnce() throws Exception {
        given(sampleService.callExternalService())
                .willReturn("Service call succeeded");

        mockMvc.perform(get("/test"));

        verify(sampleService, times(1)).callExternalService();
    }

    @Test
    void shouldReturnPlainTextContentType() throws Exception {
        given(sampleService.callExternalService())
                .willReturn("Service call succeeded");

        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN));
    }

    @Test
    void shouldReturn405WhenPostIsUsed() throws Exception {
        mockMvc.perform(post("/test"))
                .andExpect(status().isMethodNotAllowed());
    }
}
