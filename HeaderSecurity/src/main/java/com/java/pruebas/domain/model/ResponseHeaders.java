package com.java.pruebas.domain.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * Value object wrapping the headers of an HTTP response.
 * HTTP header names are case-insensitive (RFC 9110), so that rule lives here, in the domain,
 * instead of being repeated by every caller.
 */
public final class ResponseHeaders {

    private final Map<String, List<String>> values = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public ResponseHeaders(Map<String, List<String>> raw) {
        raw.forEach((name, vals) -> {
            if (name != null) {            // HttpClient exposes the status line under a null key in some cases
                values.put(name, List.copyOf(vals));
            }
        });
    }

    /** First value of the header, if present (and non-empty). */
    public Optional<String> firstValue(String headerName) {
        List<String> vals = values.get(headerName);
        return (vals == null || vals.isEmpty()) ? Optional.empty() : Optional.of(vals.get(0));
    }

    public boolean contains(String headerName) {
        return values.containsKey(headerName);
    }
}
