package com.java.pruebas.application.port.out;

import com.java.pruebas.domain.model.ResponseHeaders;

/**
 * Driven (outbound) port: how the application obtains the headers of a URL.
 * The core only states the need; HTTP, a cache, or a test fake are interchangeable adapters.
 */
@FunctionalInterface
public interface HeaderFetcher {

    ResponseHeaders fetch(String targetUrl) throws HeaderFetchException;
}
