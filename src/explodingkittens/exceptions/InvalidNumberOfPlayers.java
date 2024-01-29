package explodingkittens.exceptions;

public class InvalidNumberOfPlayers extends Exception {
    public InvalidNumberOfPlayers(int numberOfPlayers) {
        super("Cannot create game with " + numberOfPlayers + " players.");
    }
}
