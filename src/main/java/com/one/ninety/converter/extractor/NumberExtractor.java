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
        final int number = Character.getNumericValue(text.charAt(0));
        return Number.getNumber(number).name;
    }
}
