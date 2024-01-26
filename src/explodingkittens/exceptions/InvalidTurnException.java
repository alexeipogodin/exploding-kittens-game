package explodingkittens.exceptions;

public class InvalidTurnException extends Exception {
    public InvalidTurnException() {
        super("The turn is invalid");
    }
}