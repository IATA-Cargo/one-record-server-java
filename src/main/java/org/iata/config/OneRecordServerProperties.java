package org.iata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class OneRecordServerProperties {


    @Value("${server.alternative_authorities}")
    private String[] alternative_authorities;
    @Value("${server.authority}")
    private String authority;

    public static String SERVER_AUTHORITY;
    public static String[] SERVER_ALTERNATIVE_AUTHORITIES;

    @Value("${server.alternative_authorities}")
    public void setServerAlternativeAuthoritiesStatic(String[] alternativeAuthorities) {
        OneRecordServerProperties.SERVER_ALTERNATIVE_AUTHORITIES = alternativeAuthorities;
    }

    @Value("${server.authority}")
    public void setServerAuthorityStatic(String authority) {
        OneRecordServerProperties.SERVER_AUTHORITY = authority;
    }
}