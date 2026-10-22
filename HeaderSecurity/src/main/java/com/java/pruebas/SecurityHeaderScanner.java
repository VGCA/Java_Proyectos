package com.java.pruebas;

import com.java.pruebas.services.CheckerService;

import java.util.logging.Logger;

public class SecurityHeaderScanner {

    private static final Logger log = Logger.getLogger(SecurityHeaderScanner.class.getName());

    public static void main(String[] args) {
        if (args.length == 0) {
            log.info("Usage: java SecurityHeaderScanner.java <URL>");
            log.info("Example: java SecurityHeaderScanner.java https://example.com");
            return;
        }
        CheckerService checkerService = new CheckerService();

        String targetUrl = args[0];
        log.info(() -> String.format("[*] Scanning target: %s" , targetUrl + "\n"));

        checkerService.checkURL(targetUrl);
    }
}