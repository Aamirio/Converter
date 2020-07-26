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

    private void assertNumber(String inputText, String expectedOutput) {
        assertThat(numberExtractor.extract(inputText)).isEqualTo(expectedOutput);
    }
}
