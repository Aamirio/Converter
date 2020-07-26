package com.one.ninety.converter;

import com.one.ninety.converter.extractor.Extractor;

import java.io.*;

/**
 * Extracts file contents using provided Extractor and prints to console.
 *
 */
public class FileOutputter {

    private final Extractor extractor;

    public FileOutputter(Extractor extractor) { this.extractor = extractor; }

    /**
     * Prints extracted file contents to console.
     * @param filePath path name for file e.g. /Users/Bob/Docs/myFile.txt
     * @return False if unable to read file or file path is invalid. True if successfully printed to console.
     *
     */
    public boolean outputFileContents(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath).getAbsolutePath()))) {

            System.out.println();
            String line;
            while( (line = br.readLine()) != null) {
                if (!line.isEmpty()) { System.out.println(extractor.extract(line)); }
            }
            return true;
        }

        catch (FileNotFoundException e) {
            System.err.println("File not found");
            return false;
        }
        catch (IOException e) {
            System.err.println("Unable to read the file.");
            return false;
        }
    }
}
