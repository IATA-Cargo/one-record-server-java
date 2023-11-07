package org.iata.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RestUtilsTest {

    @Test
    void testCreateCompanyIdentifierFromCurrentUri() {
        assertEquals("http://1r.example.com/companies/example", RestUtils.getCompanyIdentifierFromUri("http://1r.example.com/companies/example/los"));
        assertEquals("http://1r.example.com/companies/example", RestUtils.getCompanyIdentifierFromUri("http://1r.example.com/companies/example/los/123"));
        assertEquals("http://1r.example.com/companies/example", RestUtils.getCompanyIdentifierFromUri("http://1r.example.com/companies/example"));
        assertEquals("http://1r.example.com/companies/exampl", RestUtils.getCompanyIdentifierFromUri("http://1r.example.com/companies/exampl"));
        assertEquals(null, RestUtils.getCompanyIdentifierFromUri("http://1r.example.com/companl"));
    }

}