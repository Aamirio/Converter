package com.one.ninety.converter.extractor;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberExtractorTest {

    private final NumberExtractor numberExtractor = new NumberExtractor();

    @Test
    public void shouldReturnOneNumber_whenTextContainsOneNumber() {
        assertNumber("The number 0 is single digit.", "zero");
        assertNumber("The number 5 is single digit.", "five");
    }

    @Test
    public void shouldReturnOneNumber_whenTextContainsOneNumberWithAdditionalLeadingZeros() {
        assertNumber("The number 0005 is in essence single digit.", "five");
    }

    @Test
    public void shouldReturnDoubleDigitNumber_whenTextContainsDoubleDigitNumber() {
        assertNumber("The number 36 is double digit.", "thirty-six");
        assertNumber("The number 11 is double digit less than twenty.", "eleven");
        assertNumber("The number 30 is a round double digit.", "thirty");
    }

    @Test
    public void shouldReturnTripleDigitNumber_whenTextContainsTripleDigitNumber() {
        assertNumber("The number 511 is triple digit.", "five hundred and eleven");
        assertNumber("The number 601 is triple digit.", "six hundred and one");
        assertNumber("The number 500 is a round triple digit.", "five hundred");
    }

    @Test
    public void shouldReturnFourDigitNumber_whenTextContainsFourDigitNumber() {
        assertNumber("The number 4300 is four digit.", "four thousand, three hundred");
        assertNumber("The number 4012 is four digit.", "four thousand and twelve");
        assertNumber("The number 4001 is four digit.", "four thousand and one");
        assertNumber("The number 9000 is round four digit number.", "nine thousand");
    }

    @Test
    public void shouldReturnFiveDigitNumber_whenTextContainsFiveDigitNumber() {
        assertNumber("The number 12113 is five digit.", "twelve thousand, one hundred and thirteen");
        assertNumber("The number 12013 is five digit.", "twelve thousand and thirteen");
        assertNumber("The number 12003 is five digit.", "twelve thousand and three");
        assertNumber("The number 13000 is a round five digit number.", "thirteen thousand");
    }

    @Test
    public void shouldReturnSixDigitNumber_whenTextContainsSixDigitNumber() {
        assertNumber("The number 121230 is six digit.",
                "one hundred and twenty-one thousand, two hundred and thirty");
        assertNumber("The number 121230 is six digit.",
                "one hundred and twenty-one thousand, two hundred and thirty");
        assertNumber("The number 101111 is a six digit number.",
                "one hundred and one thousand, one hundred and eleven");
        assertNumber("The number 100111 is a six digit number.",
                "one hundred thousand, one hundred and eleven");
        assertNumber("The number 100011 is a six digit number.",
                "one hundred thousand and eleven");
        assertNumber("The number 100000 is a round six digit number.",
                "one hundred thousand");
    }

    @Test
    public void shouldReturnSevenDigitNumber_whenTextContainsSevenDigitNumber() {
        assertNumber("The number 1121233 is seven digit.",
                "one million, one hundred and twenty-one thousand, two hundred and thirty-three");
        assertNumber("The number 1021230 is a seven digit number.",
                "one million, twenty-one thousand, two hundred and thirty");
        assertNumber("The number 1001230 is a seven digit number.",
                "one million, one thousand, two hundred and thirty");
        assertNumber("The number 1000230 is a seven digit number.",
                "one million, two hundred and thirty");
        assertNumber("The number 1000030 is a seven digit number.",
                "one million and thirty");
        assertNumber("The number 1000000 is seven digit.",
                "one million");
    }

    @Test
    public void shouldReturnEightDigitNumber_whenTextContainsEightDigitNumber() {
        assertNumber("The number 31121233 is eight digit.",
                "thirty-one million, one hundred and twenty-one thousand, two hundred and thirty-three");
        assertNumber("The number 11000000 is eight digit.",
                "eleven million");
    }

    @Test
    public void shouldReturnNineDigitNumber_whenTextContainsNineDigitNumber() {
        assertNumber("The number 931121233 is nine digit.",
                "nine hundred and thirty-one million, one hundred and twenty-one thousand, two hundred and thirty-three");
        assertNumber("The number 111000022 is nine digit.",
                "one hundred and eleven million and twenty-two");
        assertNumber("The number 111000000 is nine digit.",
                "one hundred and eleven million");
    }

    @Test
    public void shouldReturnTenDigitNumber_whenTextContainsTenDigitNumber() {
        assertNumber("The number 8931121233 is ten digit.",
                "eight billion, nine hundred and thirty-one million, one hundred and twenty-one thousand, two hundred and thirty-three");
        assertNumber("The number 9111000000 is ten digit.",
                "nine billion, one hundred and eleven million");
    }

    @Test
    public void shouldReturnElevenDigitNumber_whenTextContainsElevenDigitNumber() {
        assertNumber("The number 88931121233 is eleven digit.",
                "eighty-eight billion, nine hundred and thirty-one million, one hundred and twenty-one thousand, two hundred and thirty-three");
        assertNumber("The number 10000021011 is eleven digit.",
                "ten billion, twenty-one thousand and eleven");
        assertNumber("The number 10000000011 is eleven digit.",
                "ten billion and eleven");
        assertNumber("The number 10000000000 is an eleven digit round number.",
                "ten billion");
    }

    @Test
    public void shouldReturnTwelveDigitNumber_whenTextContainsTwelveDigitNumber() {
        assertNumber("The number 788931121233 is twelve digit.",
                "seven hundred and eighty-eight billion, nine hundred and thirty-one million, one hundred and twenty-one thousand, two hundred and thirty-three");
        assertNumber("The number 300000000000 is twelve digit round number.",
                "three hundred billion");
    }

    @Test
    public void shouldReturnZero_whenTextContainsMultipleZeros() {
        assertNumber("The number 000000000000 is essentially nada.", "zero");
    }

    @Test
    public void shouldReturnNumberInvalidMessage_whenTextContainsInvalidNumber() {
        assertNumber("My zip code is #65678", "number invalid");
    }

    private void assertNumber(String inputText, String expectedOutput) {
        assertThat(numberExtractor.extract(inputText)).isEqualTo(expectedOutput);
    }
}
