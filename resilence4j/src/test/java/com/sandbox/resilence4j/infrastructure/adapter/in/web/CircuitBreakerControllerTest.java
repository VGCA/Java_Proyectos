package com.sandbox.resilence4j.infrastructure.adapter.in.web;

import com.sandbox.resilence4j.application.port.in.CallExternalServiceUseCase;
import com.sandbox.resilence4j.domain.model.ServiceCallResult;
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
    private CallExternalServiceUseCase callExternalService;

    @Test
    void shouldReturn200WhenServiceSucceeds() throws Exception {
        given(callExternalService.execute())
                .willReturn(ServiceCallResult.success("Service call succeeded"));

        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("Service call succeeded"));
    }

    @Test
    void shouldPrefixMessageWhenResultIsFallback() throws Exception {
        given(callExternalService.execute())
                .willReturn(ServiceCallResult.fallback("Service failed"));

        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().string("Fallback response: Service failed"));
    }

    @Test
    void shouldInvokeUseCaseExactlyOnce() throws Exception {
        given(callExternalService.execute())
                .willReturn(ServiceCallResult.success("Service call succeeded"));

        mockMvc.perform(get("/test"));

        verify(callExternalService, times(1)).execute();
    }

    @Test
    void shouldReturnPlainTextContentType() throws Exception {
        given(callExternalService.execute())
                .willReturn(ServiceCallResult.success("Service call succeeded"));

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
