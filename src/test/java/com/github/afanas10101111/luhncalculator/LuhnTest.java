package com.github.afanas10101111.luhncalculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class LuhnTest {

    @Test
    public void cardNumberShouldBeCalculatedCorrectly() {
        assertEquals("4000 0000 0000 0002", Luhn.getFullCardNumber("4000 0000 0000 000"));
        assertEquals("40001234 0000 0016", Luhn.getFullCardNumber("40001234 0000 001"));
        assertEquals("4000 0000 56780028", Luhn.getFullCardNumber("4000 0000 5678002"));
        assertEquals("9876543210987658", Luhn.getFullCardNumber("987654321098765"));
    }

    @Test
    public void emptyInStringMustGenerateException() {
        assertThrows(NumberFormatException.class, () -> Luhn.getFullCardNumber(null));
        assertThrows(NumberFormatException.class, () -> Luhn.getFullCardNumber(""));
    }

    @Test
    public void tooLongInStringMustGenerateException() {
        StringBuilder sb = new StringBuilder("1");
        for (int i = 0; i < Luhn.MAX_INPUT_SIZE; i++) {
            sb.append("1");
        }
        String toString = sb.toString();
        assertThrows(NumberFormatException.class, () -> Luhn.getFullCardNumber(toString));
    }

    @Test
    public void inStringWithNonDigitSymbolsMustGenerateException() {
        assertThrows(NumberFormatException.class, () -> Luhn.getFullCardNumber("1a2"));
        assertThrows(NumberFormatException.class, () -> Luhn.getFullCardNumber("1!@2"));
    }
}
