package com.example.producingwebservice;

import io.spring.guides.gs_producing_web_service.GetPresidentRequest;
import io.spring.guides.gs_producing_web_service.GetPresidentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PresidentEndpoint {

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private final PresidentRepository presidentRepository;

    @Autowired
    public PresidentEndpoint(PresidentRepository presidentRepository) {
        this.presidentRepository = presidentRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPresidentRequest")
    @ResponsePayload
    public GetPresidentResponse getPresident(@RequestPayload GetPresidentRequest request) {
        GetPresidentResponse response = new GetPresidentResponse();
        response.setPresident(presidentRepository.findPresident(request.getName()));

        return response;
    }
}