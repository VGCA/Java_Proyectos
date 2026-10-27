package com.java.pruebas.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class HeaderRuleTest {

    private static final HeaderRule REQUIRED = new HeaderRule("x-frame-options", "desc", true);
    private static final HeaderRule FORBIDDEN = new HeaderRule("x-powered-by", "desc", false);

    private static ResponseHeaders headers(Map<String, List<String>> m) {
        return new ResponseHeaders(m);
    }

    @Test
    void requiredHeaderPresentPassesCaseInsensitively() {
        HeaderFinding f = REQUIRED.evaluate(headers(Map.of("X-Frame-Options", List.of("DENY"))));
        assertEquals(Verdict.PASS, f.verdict());
        assertEquals("DENY", f.observedValue());
    }

    @Test
    void requiredHeaderMissingFails() {
        HeaderFinding f = REQUIRED.evaluate(headers(Map.of()));
        assertEquals(Verdict.FAIL, f.verdict());
        assertNull(f.observedValue());
    }

    @Test
    void forbiddenHeaderPresentWarns() {
        HeaderFinding f = FORBIDDEN.evaluate(headers(Map.of("X-Powered-By", List.of("Express"))));
        assertEquals(Verdict.WARN, f.verdict());
        assertEquals("Express", f.observedValue());
    }

    @Test
    void forbiddenHeaderAbsentPasses() {
        assertEquals(Verdict.PASS, FORBIDDEN.evaluate(headers(Map.of())).verdict());
    }
}
