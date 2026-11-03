package com.sandbox.resilence4j.application.port.in;

import com.sandbox.resilence4j.domain.model.ServiceCallResult;

/** Inbound (driving) port: what the outside world can ask the application to do. */
public interface CallExternalServiceUseCase {

    ServiceCallResult execute();
}
