package com.java.pruebas.adapter.in.cli;

import com.java.pruebas.domain.model.HeaderFinding;
import com.java.pruebas.domain.model.ScanReport;

import java.util.logging.Logger;

/** Presentation concern of the CLI adapter: turns a {@link ScanReport} into log lines. */
public class ConsoleReportPrinter {

    private static final Logger log = Logger.getLogger(ConsoleReportPrinter.class.getName());

    public void print(ScanReport report) {
        log.info("--- Security Header Analysis ---");
        report.findings().forEach(this::printFinding);
    }

    private void printFinding(HeaderFinding f) {
        String name = f.rule().headerName();
        switch (f.verdict()) {
            case PASS -> log.info(() -> f.rule().shouldBePresent()
                    ? String.format("[PASS] %s is present. Value: %s", name, f.observedValue())
                    : String.format("[PASS] %s is absent (good).", name));
            case FAIL -> log.warning(() -> String.format("[FAIL] Missing recommended security header: %s", name));
            case WARN -> log.warning(() -> String.format("[WARN] Potentially sensitive header found: %s. Value: %s", name, f.observedValue()));
        }
        log.info(() -> String.format("       -> %s%n", f.rule().description()));
    }
}
