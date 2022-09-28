package org.iata.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class Utils {

    @Value("${server.alternative_authorities}:localhost:8080")
    static String[] alternativeServerAuthorities;
    @Value("${server.authority:localhost:8080}")
    static String serverAuthority;

    public static int increment(int index, int value) {
        return index + value;
    }

    public static String generateUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * Generate digit random Number of length 10
     *
     * @return 10 digit random Number from 1 to 9999999999
     */
    public static String getRandomNumberString() {
        return getRandomNumberString(10);
    }

    public static String getRandomNumberString(int n) {
        int m = (int) Math.pow(10, n - 1);
        return String.valueOf(m + new Random().nextInt(9 * m));
    }

    public static Boolean isValidURL(String url) {
        try {
            URL u = new URL(url); // this would check for the protocol
            u.toURI(); // does the extra checking required for validation of URI
        } catch (MalformedURLException e) {
            return false;
        } catch (URISyntaxException e) {
            return false;
        }
        return true;
    }

    public static String toKebabCase(String input) {

        if (input.length() == 0) {
            return "";
        }

        input = input.
                trim()
                .replaceAll("/\\z", "")
                .replaceAll("  +", " ")
                .replace("\s\s+", " ")
                .replaceAll("([a-z])([A-Z])", "$1-$2")
                .replaceAll("[\s_]+", "-")
                .toLowerCase().replace(" ", "-")
                .replace("_", "-")
        ;

        if (input.lastIndexOf("-") + 1 == input.length()) {
            input = input.substring(0, input.lastIndexOf("-"));
        }
        return input;
    }


    public static boolean containsServerAuthority(String loid) {
        if (isValidURL(loid)) {
            try {
                URL u = new URL(loid);
                String authority = u.getAuthority();
                return Set.of(alternativeServerAuthorities).contains(authority);
            } catch (MalformedURLException e) {
                return false;
            }
        }
        return false;
    }

    public static String replaceAuthority(String initialUri, String newAuthority) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(initialUri);
        if (newAuthority != null && newAuthority.length() > 0 && newAuthority.split(":").length == 2) { // e.g. host:port
            return builder.host(newAuthority.split(":")[0]).port(newAuthority.split(":")[1]).toUriString();
        } else if (newAuthority.split(":").length == 1) { // e.g. host without port
            return builder.host(newAuthority).port(null).toUriString();
        }
        return initialUri;
    }

    public static String replaceAuthorityWithServerAuthority(String initialUri) {
        return replaceAuthority(initialUri, serverAuthority);
    }
}
