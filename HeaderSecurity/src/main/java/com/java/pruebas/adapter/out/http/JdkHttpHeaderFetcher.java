package com.java.pruebas.adapter.out.http;

import com.java.pruebas.application.port.out.HeaderFetchException;
import com.java.pruebas.application.port.out.HeaderFetcher;
import com.java.pruebas.domain.model.ResponseHeaders;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/** Driven adapter: implements {@link HeaderFetcher} with the JDK's java.net.http client. */
public class JdkHttpHeaderFetcher implements HeaderFetcher {

    private final HttpClient client = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    @Override
    public ResponseHeaders fetch(String targetUrl) throws HeaderFetchException {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(targetUrl))
                    .GET()
                    .build();
            HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
            return new ResponseHeaders(response.headers().map());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();   // restore the interrupted status flag
            throw new HeaderFetchException("Scan interrupted for target: " + targetUrl, e);
        } catch (IOException | IllegalArgumentException e) {
            throw new HeaderFetchException("Failed to scan target: " + targetUrl, e);
        }
    }
}
