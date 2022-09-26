package org.iata.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilsTest {

    @Test
    void testToKebabCaseEmptyInput() {
        assertEquals("", Utils.toKebabCase(""));
    }

    @Test
    void testURLwithSpacesToKebabCaseEmptyInput() {
        assertEquals("http:-//-exa-mple.com", Utils.toKebabCase("http: // exa mple.com"));
    }

    @Test
    void testURLwithUnderscoresToKebabCaseEmptyInput() {
        assertEquals("http://example-domain.com", Utils.toKebabCase("http://example_domain.com"));
    }

    @Test
    void testURLwithUppercaseAndUnderscoresToKebabCaseEmptyInput() {
        assertEquals("http://example-domain.com", Utils.toKebabCase("HTTP://example_domain.com"));
    }

    @Test
    void testURLwithUnderscoresAndSpacesToKebabCaseEmptyInput() {
        assertEquals("http://exa-mple-domain.com", Utils.toKebabCase("http://exa    mple_domain.com"));
    }

    @Test
    void testToKebabCase1() {
        assertEquals("geeks-for-geeks", Utils.toKebabCase("Geeks For Geeks"));
    }

    @Test
    void testToKebabCase2() {
        assertEquals("geeks-for-geeks", Utils.toKebabCase("GeeksForGeeks"));
    }

    @Test
    void testToKebabCase3() {
        assertEquals("geeks-for-geeks", Utils.toKebabCase("Geeks_For_Geeks"));
    }

    @Test
    void testToKebabpCaseWithTrailingDash() {
        assertEquals("http://localhost:8080/companies/forwarder/locations/fra-acceptance-gate-2", Utils.toKebabCase("http://localhost:8080/companies/forwarder/locations/fra-acceptance-gate-2-"));
    }
    @Test
    void testToKebabpCaseWithTrailingSpace() {
        assertEquals("http://localhost:8080/companies/forwarder/locations/fra-acceptance-gate-2", Utils.toKebabCase("http://localhost:8080/companies/forwarder/locations/fra-acceptance-gate-2 "));
    }

    @Test
    void testRandomNumberLengthOne() {
        assertEquals(1, Utils.getRandomNumberString(1).length());
    }

    @Test
    void testRandomNumberLengthZero() {
        assertEquals(1, Utils.getRandomNumberString(1).length());
    }

    @Test
    void testRandomNumberLengthTwo() {
        assertEquals(2, Utils.getRandomNumberString(2).length());
    }


}