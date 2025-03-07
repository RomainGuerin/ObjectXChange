package org.esiee.util;

/**
 * Utility class providing various helper methods.
 */
public class Utils {

    /**
     * Returns the current time in milliseconds as a String.
     *
     * @return the current time in milliseconds as a String
     */
    public static String getCurrentTime() {
        return String.valueOf(System.currentTimeMillis()); //TODO : Format the time
    }
}