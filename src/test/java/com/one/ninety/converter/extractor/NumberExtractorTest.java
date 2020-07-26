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

    private void assertNumber(String inputText, String expectedOutput) {
        assertThat(numberExtractor.extract(inputText)).isEqualTo(expectedOutput);
    }
}
