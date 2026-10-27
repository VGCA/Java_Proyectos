package com.java.pruebas.domain.model;

/**
 * Result of applying one {@link HeaderRule}.
 *
 * @param rule          the rule that was evaluated
 * @param verdict       PASS / FAIL / WARN
 * @param observedValue value of the header in the response, or {@code null} when it was absent
 */
public record HeaderFinding(HeaderRule rule, Verdict verdict, String observedValue) {}
