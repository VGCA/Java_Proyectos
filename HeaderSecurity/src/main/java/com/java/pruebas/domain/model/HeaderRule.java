package com.java.pruebas.domain.model;

/**
 * A rule about one HTTP header: it either should be present (security headers)
 * or should be absent (headers that leak implementation details).
 * Formerly {@code HeaderCheck}; the evaluation logic now lives here (rich domain model).
 */
public record HeaderRule(String headerName, String description, boolean shouldBePresent) {

    public HeaderFinding evaluate(ResponseHeaders headers) {
        String value = headers.firstValue(headerName).orElse(null);
        boolean present = headers.contains(headerName);

        Verdict verdict;
        if (shouldBePresent) {
            verdict = present ? Verdict.PASS : Verdict.FAIL;
        } else {
            verdict = present ? Verdict.WARN : Verdict.PASS;
        }
        return new HeaderFinding(this, verdict, value);
    }
}
