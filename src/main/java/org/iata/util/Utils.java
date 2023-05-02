package org.iata.util;

import cz.cvut.kbss.jopa.model.annotations.OWLClass;
import cz.cvut.kbss.jsonld.JsonLd;
import org.iata.cargo.model.LogisticsObject;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import static org.iata.config.OneRecordServerProperties.SERVER_ALTERNATIVE_AUTHORITIES;
import static org.iata.config.OneRecordServerProperties.SERVER_AUTHORITY;

public class Utils {

    private static final Logger LOG = LoggerFactory.getLogger(Utils.class);

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
                return Set.of(SERVER_ALTERNATIVE_AUTHORITIES).contains(authority);
            } catch (MalformedURLException e) {
                return false;
            }
        }
        return false;
    }

    public static String replaceAuthority(String initialUri, String newAuthority) {
        if (newAuthority == null || newAuthority.length() < 1) {
            return initialUri;
        }
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(initialUri);
        if (newAuthority != null && newAuthority.length() > 0 && newAuthority.split(":").length == 2) { // e.g. host:port
            return builder.host(newAuthority.split(":")[0]).port(newAuthority.split(":")[1]).toUriString();
        } else if (newAuthority.split(":").length == 1) { // e.g. host without port
            return builder.host(newAuthority).port(null).toUriString();
        }
        return initialUri;
    }

    public static String replaceAuthorityWithServerAuthority(String initialUri) {
        return replaceAuthority(initialUri, SERVER_AUTHORITY);
    }

    public static String getCanonicalNameByLogisticsObjectIRI(String iri) {
        Reflections reflections = new Reflections(new ConfigurationBuilder().forPackages("org.iata.cargo.model"));
        Set<Class<? extends LogisticsObject>> classes = reflections.getSubTypesOf(LogisticsObject.class);
        for (Class c : classes) {
            Annotation logisticsObjectClassAnnotation = Arrays.stream(c.getDeclaredAnnotations()).filter(a -> a.annotationType() == OWLClass.class).findAny().orElse(null);
            if (logisticsObjectClassAnnotation != null && logisticsObjectClassAnnotation.annotationType() == OWLClass.class && ((OWLClass) logisticsObjectClassAnnotation).iri().equals(iri)) {
                return c.getCanonicalName();
            }
        }
        return null;
    }

    public static String retrieveContentFromURL(String remote_url) {
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(remote_url);
            LOG.error("GET: " + url.toString());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("User-Agent", "one-record-server-java");
            connection.setRequestProperty("Content-Type", JsonLd.MEDIA_TYPE);

            // Get Response
            connection.connect();

            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("Error occurred while retrieving logistics object from publisher " + e.getMessage());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
