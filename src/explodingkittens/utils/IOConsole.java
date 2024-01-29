package explodingkittens.utils;

import explodingkittens.exceptions.InvalidInputException;

import java.io.*;

/**
 * A simple console input/output utility class.
 */
public class IOConsole {
    public final BufferedReader in;
    public final PrintStream out;
    private final String promptMark;

    /**
     * Constructs an IOConsole with the specified input and output streams and a prompt mark.
     *
     * @param in         The input stream.
     * @param out        The output stream.
     * @param promptMark The prompt mark.
     */
    public IOConsole(BufferedReader in, PrintStream out, String promptMark) {
        this.in = in;
        this.out = out;
        this.promptMark = promptMark;
    }

    /**
     * Constructs an IOConsole with a specified prompt mark and default input and output streams.
     *
     * @param promptMark The prompt mark.
     */
    public IOConsole(String promptMark) {
        this(new BufferedReader(new InputStreamReader(System.in)), System.out, promptMark);
    }

    /**
     * Constructs an IOConsole with default input and output streams and a default prompt mark ("\n >> ").
     */
    public IOConsole() {
        this(new BufferedReader(new InputStreamReader(System.in)), System.out,
                "\n" + ConsoleColors.GREEN_BOLD_BRIGHT + " >> " + ConsoleColors.RESET);
    }

    /**
     * Prints an error message with red background color.
     *
     * @param message The error message to be printed.
     */
    public void printErr(String message) {
        out.print(ConsoleColors.RED_BACKGROUND + message + ConsoleColors.RESET);
    }

    /**
     * Prints an error message with red background color and adds a newline.
     *
     * @param message The error message to be printed.
     */
    public void printlnErr(String message) {
        printErr(message + "\n");
    }

    /**
     * Prints a success message with green bold bright color.
     *
     * @param message The success message to be printed.
     */
    public void printSucc(String message) {
        out.print(ConsoleColors.GREEN_BOLD_BRIGHT + message + ConsoleColors.RESET);
    }

    /**
     * Prints a success message with green bold bright color and adds a newline.
     *
     * @param message The success message to be printed.
     */
    public void printlnSucc(String message) {
        printSucc(message + "\n");
    }

    /**
     * Reads a string from the console.
     *
     * @return The user-inputted string.
     */
    public String readString() {
        try {
            return in.readLine();
        } catch (IOException e) {
            out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Reads a string from the console with a prompt message.
     *
     * @param promptMessage The prompt message.
     * @return The user-inputted string.
     */
    public String readString(String promptMessage) {
        out.print(promptMessage + promptMark);
        return readString();
    }

    /**
     * Reads a character from the console.
     *
     * @return The user-inputted character.
     */
    public char readChar() {
        try {
            return readString().charAt(0);
        } catch (StringIndexOutOfBoundsException e) {
            return ' ';
        }
    }

    /**
     * Reads a character from the console with a prompt message.
     *
     * @param promptMessage The prompt message.
     * @return The user-inputted character.
     */
    public char readChar(String promptMessage) {
        out.print("\n" + promptMessage + promptMark);
        return readChar();
    }

    /**
     * Reads an integer from the console.
     *
     * @return The user-inputted integer.
     * @throws NumberFormatException If the input is not a valid integer.
     */
    public int readInt() throws NumberFormatException {
        return Integer.parseInt(readString());
    }

    /**
     * Reads an integer from the console with a prompt message.
     *
     * @param promptMessage The prompt message.
     * @return The user-inputted integer.
     * @throws NumberFormatException If the input is not a valid integer.
     */
    public int readInt(String promptMessage) throws NumberFormatException {
        out.print("\n" + promptMessage + promptMark);
        return readInt();
    }

    /**
     * Reads a double from the console.
     *
     * @return The user-inputted double.
     * @throws NumberFormatException If the input is not a valid double.
     */
    public double readDouble() throws NumberFormatException {
        return Double.parseDouble(readString());
    }

    /**
     * Reads a double from the console with a prompt message.
     *
     * @param promptMessage The prompt message.
     * @return The user-inputted double.
     * @throws NumberFormatException If the input is not a valid double.
     */
    public double readDouble(String promptMessage) throws NumberFormatException {
        out.print("\n" + promptMessage + promptMark);
        return readDouble();
    }

    /**
     * Checks if the user input string equals a specified value.
     *
     * @param s The expected value.
     * @return True if the input equals the specified value, false otherwise.
     */
    public boolean equalsIn(String s) {
        return s.equals(readString());
    }

    /**
     * Checks if the user input string equals a specified value, ignoring case.
     *
     * @param s The expected value.
     * @return True if the input equals the specified value (case-insensitive), false otherwise.
     */
    public boolean equalsIgnoreCaseIn(String s) {
        return s.equalsIgnoreCase(readString());
    }

    /**
     * Checks if the user input character equals a specified value, ignoring case.
     *
     * @param c The expected value.
     * @return True if the input equals the specified value (case-insensitive), false otherwise.
     */
    public boolean equalsIgnoreCaseIn(char c) {
        Character inC = Character.toLowerCase(readString().charAt(0));
        return inC.equals(Character.toLowerCase(c));
    }

    /**
     * Prompts the user to accept or deny a specified character.
     *
     * @param accept The character representing acceptance.
     * @param deny   The character representing denial.
     * @return True if the user accepts, false if the user denies.
     * @throws InvalidInputException If the user input is neither accept nor deny.
     */
    public boolean userAccept(Character accept, Character deny) throws InvalidInputException {
        Character inC = readChar();

        accept = Character.toLowerCase(accept);
        deny = Character.toLowerCase(deny);

        if (!(inC == accept || inC == deny)) {
            throw new InvalidInputException(
                    Character.toString(accept), Character.toString(deny),
                    Character.toString(inC)
            );
        }

        return inC.equals(accept);
    }

    /**
     * Prompts the user with a message to accept or deny a specified character.
     *
     * @param prompt The prompt message.
     * @param accept The character representing acceptance.
     * @param deny   The character representing denial.
     * @return True if the user accepts, false if the user denies.
     * @throws InvalidInputException If the user input is neither accept nor deny.
     */
    public boolean userAccept(String prompt, Character accept, Character deny) throws InvalidInputException {
        String promptLine = prompt + " (" + Character.toUpperCase(accept) + "/" +
                Character.toLowerCase(deny) + ") ";
        out.print(promptLine);
        return userAccept(accept, deny);
    }
}