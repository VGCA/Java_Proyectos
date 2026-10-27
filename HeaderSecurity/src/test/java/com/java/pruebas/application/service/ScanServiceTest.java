package com.java.pruebas.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.java.pruebas.application.port.out.HeaderFetchException;
import com.java.pruebas.application.port.out.HeaderFetcher;
import com.java.pruebas.domain.model.HeaderRule;
import com.java.pruebas.domain.model.ResponseHeaders;
import com.java.pruebas.domain.model.ScanReport;
import com.java.pruebas.domain.model.Verdict;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

/** The whole use case is tested with a fake outbound port: no network, no HTTP client. */
class ScanServiceTest {

    private static final List<HeaderRule> RULES = List.of(
            new HeaderRule("x-frame-options", "clickjacking", true),
            new HeaderRule("x-powered-by", "tech leak", false));

    @Test
    void cleanSiteProducesCleanReport() throws Exception {
        HeaderFetcher fake = url -> new ResponseHeaders(Map.of("x-frame-options", List.of("DENY")));

        ScanReport report = new ScanService(fake, RULES).scan("https://example.com");

        assertTrue(report.isClean());
        assertEquals(2, report.findings().size());
    }

    @Test
    void problemsAreReportedPerRule() throws Exception {
        HeaderFetcher fake = url -> new ResponseHeaders(Map.of("X-Powered-By", List.of("PHP/8")));

        ScanReport report = new ScanService(fake, RULES).scan("https://example.com");

        assertFalse(report.isClean());
        assertEquals(Verdict.FAIL, report.findings().get(0).verdict());
        assertEquals(Verdict.WARN, report.findings().get(1).verdict());
    }

    @Test
    void fetchFailurePropagatesThroughThePort() {
        HeaderFetcher failing = url -> { throw new HeaderFetchException("boom", null); };

        assertThrows(HeaderFetchException.class, () -> new ScanService(failing, RULES).scan("https://x"));
    }
}
