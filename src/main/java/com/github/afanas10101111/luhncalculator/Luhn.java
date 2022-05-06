package com.github.afanas10101111.luhncalculator;

public class Luhn {
    public static final int MAX_INPUT_SIZE = 30;

    private int controlNumber;
    private int charCurrentIndex;

    private Luhn() {
    }

    public static String getFullCardNumber(String inString) {
        return new Luhn().calculateFullCardNumber(inString);
    }

    private String calculateFullCardNumber(String inString) {
        String formatted = formatInString(inString);
        char[] chars = formatted.toCharArray();
        for (charCurrentIndex = 0; charCurrentIndex < chars.length; charCurrentIndex++) {
            controlNumberIntermediateCalculation(chars[charCurrentIndex]);
        }
        controlNumberFinalCalculation();
        return inString + controlNumber;
    }

    private String formatInString(String inString) {
        if (inString == null || inString.isEmpty()) {
            throw new NumberFormatException("Empty argument! Card number expecting.");
        }
        String withoutWhiteSpaces = inString.replace(" ", "");
        if (withoutWhiteSpaces.length() > MAX_INPUT_SIZE) {
            throw new NumberFormatException(
                    String.format("Card number must be less or equal %d digits!", MAX_INPUT_SIZE)
            );
        }
        return new StringBuilder(withoutWhiteSpaces).reverse().toString();
    }

    private void controlNumberIntermediateCalculation(char c) {
        if (charCurrentIndex % 2 == 0) {
            int tmp = numberFromChar(c) * 2;
            controlNumber += tmp > 9 ? tmp - 9 : tmp;
        } else {
            controlNumber += numberFromChar(c);
        }
    }

    private void controlNumberFinalCalculation() {
        int tmp = controlNumber % 10;
        controlNumber = tmp == 0 ? 0 : 10 - tmp;
    }

    private int numberFromChar(char c) {
        if (charIsNotNumber(c)) {
            throw new NumberFormatException("Card number must contain digits only!");
        }
        return c - 48;
    }

    private boolean charIsNotNumber(char c) {
        return c < 48 || c > 58;
    }
}
