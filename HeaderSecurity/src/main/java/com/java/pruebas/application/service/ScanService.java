package com.java.pruebas.application.service;

import com.java.pruebas.application.port.in.ScanUrlUseCase;
import com.java.pruebas.application.port.out.HeaderFetchException;
import com.java.pruebas.application.port.out.HeaderFetcher;
import com.java.pruebas.domain.model.HeaderFinding;
import com.java.pruebas.domain.model.HeaderRule;
import com.java.pruebas.domain.model.ResponseHeaders;
import com.java.pruebas.domain.model.ScanReport;

import java.util.List;

/** Implements the use case by orchestrating the outbound port and the domain rules. */
public class ScanService implements ScanUrlUseCase {

    private final HeaderFetcher fetcher;
    private final List<HeaderRule> rules;

    public ScanService(HeaderFetcher fetcher, List<HeaderRule> rules) {
        this.fetcher = fetcher;
        this.rules = List.copyOf(rules);
    }

    @Override
    public ScanReport scan(String targetUrl) throws HeaderFetchException {
        ResponseHeaders headers = fetcher.fetch(targetUrl);
        List<HeaderFinding> findings = rules.stream()
                .map(rule -> rule.evaluate(headers))
                .toList();
        return new ScanReport(targetUrl, findings);
    }
}
