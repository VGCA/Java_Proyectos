package com.java.pruebas.domain.model;

import java.util.List;

/** Outcome of scanning one target: every finding, in rule order. */
public record ScanReport(String targetUrl, List<HeaderFinding> findings) {

    public ScanReport {
        findings = List.copyOf(findings);
    }

    public boolean isClean() {
        return findings.stream().allMatch(f -> f.verdict() == Verdict.PASS);
    }
}
