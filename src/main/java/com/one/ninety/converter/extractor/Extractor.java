package com.one.ninety.converter.extractor;

/**
 * An extractor used to extract parts of a given text
 */
public interface Extractor {

    /**
     * Extracts parts of a given text. Can be converted into any desired format and returned.
     * @param text Given text to extract information from
     * @return extracted text
     */
    String extract(String text);
}
