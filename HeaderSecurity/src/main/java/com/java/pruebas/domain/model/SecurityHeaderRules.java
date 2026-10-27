package com.java.pruebas.domain.model;

import java.util.List;

/** The default rule set (formerly {@code utils.ToCheck}). It is domain knowledge, not a utility. */
public final class SecurityHeaderRules {

    private SecurityHeaderRules() {}

    public static List<HeaderRule> defaults() {
        return List.of(
                new HeaderRule("strict-transport-security", "Enforces HTTPS and protects against protocol downgrade attacks.", true),
                new HeaderRule("content-security-policy", "Mitigates XSS and data injection attacks by restricting resource loads.", true),
                new HeaderRule("x-frame-options", "Protects against clickjacking by preventing framing of the page.", true),
                new HeaderRule("x-content-type-options", "Prevents browsers from MIME-sniffing away from the declared content type.", true),
                new HeaderRule("x-powered-by", "Leaks backend technology stack details (Best practice: Remove this).", false)
        );
    }
}
