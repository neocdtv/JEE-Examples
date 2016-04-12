/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server;

import java.util.Collection;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author xix
 */
public final class MyStringUtils {

    private static final char STAR_CHAR = '*';
    private static final char WILDCARD_DB_CHAR = '%';
    private static final String[] SQL_WILDCARD_CHARACTERS = new String[]{"%", "_", "[", "]"};
    private static final char SQL_ESCAPE_CHARACTER = '/';
    private static final String[] ESCAPED_SQL_WILDCARD_CHARACTERS;

    static {
        ESCAPED_SQL_WILDCARD_CHARACTERS = new String[SQL_WILDCARD_CHARACTERS.length];
        for (int index = 0; index < SQL_WILDCARD_CHARACTERS.length; index++) {
            ESCAPED_SQL_WILDCARD_CHARACTERS[index] = SQL_ESCAPE_CHARACTER + SQL_WILDCARD_CHARACTERS[index];
        }
    }

    private MyStringUtils() {
        // only static methods
    }

    public static String camelCaseToUpperCase(final String camelCaseString) {
        if (StringUtils.isBlank(camelCaseString)) {
            return "";
        }
        String result = camelCaseString.replaceAll("([A-Z])", "_$1");
        if (result.startsWith("_")) {
            result = result.substring(1);
        }
        return result.toUpperCase();
    }

    public static String toCamelCaseWithInitialUppercase(final String string) {
        if (StringUtils.isBlank(string)) {
            return "";
        }

        if (string.length() == 1) {
            return string.toUpperCase();
        } else {

            final String camelCase = toCamelCase(string);

            return camelCase.substring(0, 1).toUpperCase() + camelCase.substring(1);
        }
    }

    public static String toCamelCase(final String value) {

        final StringBuilder sb = new StringBuilder();

        final char delimChar = '_';
        boolean lower = false;

        for (int charInd = 0; charInd < value.length(); ++charInd) {

            final char valueChar = value.charAt(charInd);

            if (valueChar == delimChar) {
                lower = false;
            } else if (lower) {
                sb.append(Character.toLowerCase(valueChar));
            } else {
                sb.append(Character.toUpperCase(valueChar));
                lower = true;
            }
        }

        return sb.replace(0, 1, sb.substring(0, 1).toLowerCase()).toString();
    }

    /**
     * Replaces the '*' signs in String to the wildcard sign '%'.
     *
     * @param containingStarSigns string containing start signs.
     * @return string ready to execute against DB ( containing DB wildcard sign
     * )
     */
    public static String convertToWildcardString(final String containingStarSigns) {
        return containingStarSigns.replace(STAR_CHAR, WILDCARD_DB_CHAR);
    }

    /**
     * Checks if given string contains a wildcard sign (*).
     *
     * @param textToCheck string to check for wildcards
     * @return true if wildcard sign is found in the given text.
     */
    public static boolean containsWildcards(final String textToCheck) {
        return textToCheck.indexOf(STAR_CHAR) > -1;
    }

    /**
     * Concatenate any collection values as string with any separator
     *
     * @param values - Collection
     * @param separator - String
     * @return concatenated value - String
     */
    public static String asSeparatedString(final Collection<String> values, final String separator) {
        if (values == null) {
            return null;
        }
        final StringBuilder stringBuilder = new StringBuilder();
        for (final String value : values) {
            if (StringUtils.isNotBlank(value)) {
                stringBuilder.append(value).append(separator);
            }
        }
        removeLastSeparator(separator, stringBuilder);
        return stringBuilder.toString().trim();
    }

    private static void removeLastSeparator(final String separator, final StringBuilder stringBuilder) {
        if (stringBuilder.length() > 0) {
            final int lastSeparatorIndex = stringBuilder.lastIndexOf(separator);
            if (lastSeparatorIndex == stringBuilder.length() - 1) {
                stringBuilder.deleteCharAt(lastSeparatorIndex);
            }
        }
    }

    public static String escapeSQLWildcard(final String sqlString) {
        return StringUtils.replaceEach(sqlString, SQL_WILDCARD_CHARACTERS, ESCAPED_SQL_WILDCARD_CHARACTERS);
    }
}
