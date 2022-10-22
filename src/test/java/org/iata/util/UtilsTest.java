package org.iata.util;

import org.iata.api.model.Notification;
import org.iata.cargo.model.LogisticsObject;
import org.iata.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.iata.util.Utils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
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

    @Test
    void testContainsServerHostnameLocalhost() {
        String loid = "http://localhost:8080/companies/forwarder/locations/fra-acceptance-gate-2-";
        assertTrue(containsServerAuthority(loid));
    }

    @Test
    void testContainsServerHostnameWrongHostname() {
        String loid2 = "http://example/companies/forwarder/locations/fra-acceptance-gate-2-";
        assertFalse(containsServerAuthority(loid2));
    }

    @Test
    void testReplaceAuthority() {
        assertEquals("http://test/companies/forwarder/locations/fra-acceptance-gate-2-", replaceAuthority("http://example/companies/forwarder/locations/fra-acceptance-gate-2-", "test"));
        assertEquals("http://test:8080/companies/forwarder/locations/fra-acceptance-gate-2-", replaceAuthority("http://example/companies/forwarder/locations/fra-acceptance-gate-2-", "test:8080"));
        assertEquals("http://test:8080/companies/forwarder/locations/fra-acceptance-gate-2-", replaceAuthority("http://example:8080/companies/forwarder/locations/fra-acceptance-gate-2-", "test:8080"));
        assertEquals("http://example/companies/forwarder/locations/fra-acceptance-gate-2-", replaceAuthority("http://example:8080/companies/forwarder/locations/fra-acceptance-gate-2-", "example"));
    }

    @Test
    void testReplaceAuthorityWithServerAuthority() {
        assertEquals("http://localhost:8080/companies/forwarder/locations/fra-acceptance-gate-2-", replaceAuthorityWithServerAuthority("http://example/companies/forwarder/locations/fra-acceptance-gate-2-"));
    }

    @Test
    void testGetCanonicalNameByLogisticsObjectIRI() {
        assertEquals("org.iata.cargo.model.Piece", getCanonicalNameByLogisticsObjectIRI("https://onerecord.iata.org/Piece"));
        assertEquals("org.iata.cargo.model.Shipment", getCanonicalNameByLogisticsObjectIRI("https://onerecord.iata.org/Shipment"));
        assertEquals("org.iata.cargo.model.ULD", getCanonicalNameByLogisticsObjectIRI("https://onerecord.iata.org/ULD"));
    }

//    @Test
//    void testRetrieveContentFromURL() {
//        String content = Utils.retrieveContentFromURL("http://localhost:8080/companies");
//        assertTrue(content != null);
//        assertTrue(content.length() > 0);
//    }


}