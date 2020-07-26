package com.one.ninety.converter;

import com.one.ninety.converter.extractor.Extractor;
import com.one.ninety.converter.extractor.NumberExtractor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FileOutputterTest {

    private final Extractor extractor = new NumberExtractor();
    private final FileOutputter fileOutputter = new FileOutputter(extractor);
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errorOutput = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
        System.setErr(new PrintStream(errorOutput));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    public void shouldPrintExtractedFileContents_whenValidFilePathProvided() {

        final String filePath = "src/test/resources/test.txt";
        final String expectedOutput = "\n" +
                "five hundred and thirty-six\n" +
                "nine thousand, one hundred and twenty-one\n" +
                "number invalid\n" +
                "ten thousand and twenty-two\n" +
                "sixty-six billion, seven hundred and twenty-three million, one hundred and seven thousand and eight\n" +
                "twenty-three\n";

        assertThat(fileOutputter.outputFileContents(filePath)).isTrue();
        assertThat(output.toString()).isEqualTo(expectedOutput);
    }

    @Test
    public void shouldPrintError_whenInvalidFilePathProvided() {

        final String filePath = "src/test/resources/iDoNotExist.txt";
        final String expectedOutput = "File not found\n";

        assertThat(fileOutputter.outputFileContents(filePath)).isFalse();
        assertThat(output.toString()).isBlank();
        assertThat(errorOutput.toString()).isEqualTo(expectedOutput);
    }
}
