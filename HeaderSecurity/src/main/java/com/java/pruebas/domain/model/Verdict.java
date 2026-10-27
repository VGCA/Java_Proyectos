package com.java.pruebas.domain.model;

/** Outcome of evaluating one {@link HeaderRule} against a response. */
public enum Verdict {
    /** The header situation matches the recommendation. */
    PASS,
    /** A recommended header is missing. */
    FAIL,
    /** A header that should not be exposed is present. */
    WARN
}
