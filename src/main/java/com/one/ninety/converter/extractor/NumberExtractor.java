package com.one.ninety.converter.extractor;

import java.util.Arrays;

/**
 * Extracts first number from given text and returns the number as words.
 */
public class NumberExtractor implements Extractor {

    /**
     * Extracts first number from given text and returns the number as words.
     * @param text Text to extract number from.
     * @return The number as words. Returns "number invalid" if a valid number is not provided.
     */
    public String extract(String text) {

        return Arrays.stream(text.split(" "))
                .filter(this::isNumeric)
                .map(this::convert)
                .findFirst()
                .orElse("number invalid");
    }

    private boolean isNumeric(String text) {

        if (text == null || text.equals("")) { return false; }

        return text.chars().allMatch(Character::isDigit);
    }

    private String convert(String text) {

        //Remove all leading zeros
        text = text.trim().replaceFirst("^0+(?!$)", "");

        final String valueForHundreds = text.length() > 3 ? getValueForHundreds(text.substring(0, 3)) : "";
        final String valueForTens = text.length() > 2 ? getValueForTens(text.substring(0, 2)) : "";
        final String valueForOnes = text.length() > 1 ? getValueForOnes(text.substring(0, 1)) : "";

        switch (text.length()) {
            case  9: return getValueForMillions(valueForHundreds, text.substring(3));
            case  8: return getValueForMillions(valueForTens, text.substring(2));
            case  7: return getValueForMillions(valueForOnes, text.substring(1));
            case  6: return getValueForThousands(valueForHundreds, text.substring(3));
            case  5: return getValueForThousands(valueForTens, text.substring(2));
            case  4: return getValueForThousands(valueForOnes, text.substring(1));
            case  3: return getValueForHundreds(text);
            case  2: return getValueForTens(text);
            case  1: return getValueForOnes(text);
            default: return "";
        }
    }

    private String getValueForMillions(String millions, String thousands) {

        final StringBuilder stringBuilder = new StringBuilder();
        final String valueForThousands = getValueForThousands(getValueForHundreds(thousands.substring(0, 3)), thousands.substring(3));

        if (!millions.isEmpty()) {
            stringBuilder.append(millions);
            stringBuilder.append(" million");
        }

        if (thousands.equals("000000")) { return stringBuilder.toString(); }

        if (!valueForThousands.startsWith(" and")) { stringBuilder.append(", "); }
        stringBuilder.append(valueForThousands);

        return stringBuilder.toString();
    }

    private String getValueForThousands(String thousands, String hundreds) {

        final StringBuilder stringBuilder = new StringBuilder();
        final String valueForHundreds = getValueForHundreds(hundreds);

        if (!thousands.isEmpty()) {
            stringBuilder.append(thousands);
            stringBuilder.append(" thousand");
        }

        if (hundreds.equals("000")) { return stringBuilder.toString(); }

        if (valueForHundreds.contains("hundred")) {
            stringBuilder.append( !thousands.isEmpty() ? ", " : "");
        }
        else { stringBuilder.append( " and "); }

        stringBuilder.append(valueForHundreds);

        return stringBuilder.toString();
    }

    private String getValueForHundreds(String text) {

        final StringBuilder stringBuilder = new StringBuilder();

        if (!text.startsWith("0")) {
            stringBuilder.append(getValueForOnes(text.substring(0,1)));
            stringBuilder.append(" hundred");
        }

        if (text.substring(1, 3).equals("00")) { return stringBuilder.toString(); }

        if (!text.startsWith("0")) { stringBuilder.append(" and "); }

        stringBuilder.append(getValueForTens(text.substring(1, 3)));

        return stringBuilder.toString();
    }

    private String getValueForTens(String text) {

        final StringBuilder stringBuilder = new StringBuilder();
        final Number number = Number.getNumber(Character.getNumericValue(text.charAt(0)) * 10);

        if (text.charAt(0) == '0') { return getValueForOnes(text.substring(1)); }
        if (text.charAt(0) == '1') { return Number.getNumber(Integer.parseInt(text)).name; }

        stringBuilder.append(number.name);

        if (text.charAt(1) != '0') {
            stringBuilder.append('-');
            stringBuilder.append(getValueForOnes(text.substring(1)));
            return stringBuilder.toString();
        }

        return stringBuilder.toString();
    }

    private String getValueForOnes(String text) {
        final int number = Character.getNumericValue(text.charAt(0));
        return Number.getNumber(number).name;
    }
}
