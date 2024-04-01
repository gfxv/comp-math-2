package dev.gfxv.utils;

import dev.gfxv.exceptions.InvalidInputException;

public class Parser {

    public static int parseInt(String data) throws InvalidInputException {
        try {
            return Integer.parseInt(data);
        } catch(NumberFormatException ignore) {
            throw new InvalidInputException(data +  "is not a number");
        }
    }
}
