package com.sandbox.resilence4j.application.port.out;

import com.sandbox.resilence4j.domain.exception.ServiceUnavailableException;

/** Outbound (driven) port: what the application needs from the outside world. */
public interface ExternalServicePort {

    /**
     * @return the service's answer
     * @throws ServiceUnavailableException if the service cannot answer
     */
    String call();
}
