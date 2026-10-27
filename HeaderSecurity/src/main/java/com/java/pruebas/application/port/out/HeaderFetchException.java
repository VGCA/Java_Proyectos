package com.java.pruebas.application.port.out;

/**
 * Technology-neutral failure of the {@link HeaderFetcher} port.
 * Adapters translate their own exceptions (IOException, IllegalArgumentException...) into this one,
 * so the core never depends on java.net.
 */
public class HeaderFetchException extends Exception {

    public HeaderFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}
