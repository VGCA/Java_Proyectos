package com.java.pruebas.utils;

import com.java.pruebas.model.HeaderCheck;

import java.util.List;

public class ToCheck {

    private ToCheck(){}

    public static final List<HeaderCheck> CHECKS = List.of(
            new HeaderCheck("strict-transport-security", "Enforces HTTPS and protects against protocol downgrade attacks.", true),
            new HeaderCheck("content-security-policy", "Mitigates XSS and data injection attacks by restricting resource loads.", true),
            new HeaderCheck("x-frame-options", "Protects against clickjacking by preventing framing of the page.", true),
            new HeaderCheck("x-content-type-options", "Prevents browsers from MIME-sniffing away from the declared content type.", true),
            new HeaderCheck("x-powered-by", "Leaks backend technology stack details (Best practice: Remove this).", false)
    );
}
