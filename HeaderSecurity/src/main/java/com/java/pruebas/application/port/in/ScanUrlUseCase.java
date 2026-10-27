package com.java.pruebas.application.port.in;

import com.java.pruebas.application.port.out.HeaderFetchException;
import com.java.pruebas.domain.model.ScanReport;

/** Driving (inbound) port: what the outside world can ask the application to do. */
public interface ScanUrlUseCase {

    ScanReport scan(String targetUrl) throws HeaderFetchException;
}
