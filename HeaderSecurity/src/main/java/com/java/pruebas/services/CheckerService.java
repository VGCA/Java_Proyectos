package com.java.pruebas.services;

import com.java.pruebas.model.HeaderCheck;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.java.pruebas.utils.ToCheck.CHECKS;

public class CheckerService {

    private static final Logger log = Logger.getLogger(CheckerService.class.getName());

    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public void checkURL(String targetUrl) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(targetUrl))
                    .GET()
                    .build();

            HttpResponse<Void> response = CLIENT.send(request, HttpResponse.BodyHandlers.discarding());
            Map<String, List<String>> headers = response.headers().map();

            log.info("--- Security Header Analysis ---");
            for (HeaderCheck check : CHECKS) {
                evaluateHeader(headers, check);
            }

        } catch (InterruptedException e) {
            // Restore the interrupted status flag
            Thread.currentThread().interrupt();
            log.log(Level.SEVERE, e, () -> "Scan interrupted for target: " + targetUrl);
        } catch (Exception e) {
            log.log(Level.SEVERE, e, () -> "Failed to scan target: " + targetUrl);
        }
    }

    private void evaluateHeader(Map<String, List<String>> headers, HeaderCheck check) {
        String matchedKey = headers.keySet().stream()
                .filter(k -> k.equalsIgnoreCase(check.headerName()))
                .findFirst()
                .orElse(null);

        if (check.shouldBePresent()) {
            if (matchedKey != null) {
                String value = headers.get(matchedKey).get(0);
                log.info(() -> String.format("[PASS] %s is present. Value: %s", check.headerName(), value));
            } else {

                log.warning(() -> String.format("[FAIL] Missing recommended security header: %s", check.headerName()));
            }
        } else {
            if (matchedKey != null) {
                String value = headers.get(matchedKey).get(0);

                log.warning(() -> String.format("[WARN] Potentially sensitive header found: %s. Value: %s", check.headerName(), value));
            } else {
                log.info(() -> String.format("[PASS] %s is absent (good).", check.headerName()));
            }
        }
        log.info(() -> String.format("       -> %s%n", check.description()));
    }
}