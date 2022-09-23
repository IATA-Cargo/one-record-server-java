package org.iata.util;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.UUID;

public class Utils {

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

}
