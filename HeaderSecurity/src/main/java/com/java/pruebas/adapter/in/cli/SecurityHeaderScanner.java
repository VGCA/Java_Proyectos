package com.java.pruebas.adapter.in.cli;

import com.java.pruebas.adapter.out.http.JdkHttpHeaderFetcher;
import com.java.pruebas.application.port.in.ScanUrlUseCase;
import com.java.pruebas.application.port.out.HeaderFetchException;
import com.java.pruebas.application.service.ScanService;
import com.java.pruebas.domain.model.SecurityHeaderRules;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Driving adapter (command line) AND composition root: the only place that knows
 * which concrete adapter is plugged into which port.
 */
public class SecurityHeaderScanner {

    private static final Logger log = Logger.getLogger(SecurityHeaderScanner.class.getName());

    private final ScanUrlUseCase scanUrl;
    private final ConsoleReportPrinter printer;

    public SecurityHeaderScanner(ScanUrlUseCase scanUrl, ConsoleReportPrinter printer) {
        this.scanUrl = scanUrl;
        this.printer = printer;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            log.info("Usage: java -cp target/classes com.java.pruebas.adapter.in.cli.SecurityHeaderScanner <URL>");
            log.info("Example: ... SecurityHeaderScanner https://example.com");
            return;
        }

        // Wiring: adapter (HTTP) -> port (HeaderFetcher) -> use case -> port (ScanUrlUseCase) -> adapter (CLI)
        ScanUrlUseCase useCase = new ScanService(new JdkHttpHeaderFetcher(), SecurityHeaderRules.defaults());
        new SecurityHeaderScanner(useCase, new ConsoleReportPrinter()).run(args[0]);
    }

    void run(String targetUrl) {
        log.info(() -> "[*] Scanning target: " + targetUrl + "\n");
        try {
            printer.print(scanUrl.scan(targetUrl));
        } catch (HeaderFetchException e) {
            log.log(Level.SEVERE, e, e::getMessage);
        }
    }
}
