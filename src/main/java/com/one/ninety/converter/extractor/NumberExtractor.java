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

        switch (text.length()) {
            case  2: return getValueForTens(text);
            case  1: return getValueForOnes(text);
            default: return "";
        }
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
