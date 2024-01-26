package explodingkittens.exceptions;

import java.io.IOException;

public class InvalidInputException extends IOException {
    public static final String DEFAULT_MESSAGE = "Invalid input.";
    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String expected, String actual) {
        this(
                DEFAULT_MESSAGE +
                        " Expected '" + expected +
                        "', but got '" + actual + "'.\n"
        );
    }

    public InvalidInputException(String expected1, String expected2, String actual) {
        this(
                DEFAULT_MESSAGE +
                        " Expected '" + expected1 + "' or '" + expected2 +
                        "', but got '" + actual + "'"
        );
    }
}
