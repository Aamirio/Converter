package com.one.ninety.converter;

import com.one.ninety.converter.extractor.NumberExtractor;

import java.util.Scanner;

/**
 * Main entry point to run the application
 */
public class ConverterApp {

    private static final FileOutputter outputter = new FileOutputter(new NumberExtractor());

    public static void main(String[] args) {
        renderCommandPrompt();
        readInput(new Scanner(System.in));
    }

    private static void readInput(Scanner userInput) {

        final String token = userInput.next();

        if ("Q".equals(token)) { System.exit(0); }
        else {
            if (!outputter.outputFileContents(token)) System.out.println("Please try again");
        }

        renderCommandPrompt();

        if (userInput.hasNext()) { readInput(userInput); }
    }

    private static void renderCommandPrompt() {
        System.out.println("\nPlease provide a file path and hit Enter\nOr hit 'Q' and hit Enter to exit from the program");
    }
}
