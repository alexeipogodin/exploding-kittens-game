package explodingkittens.exceptions;

import explodingkittens._players.Player;

public class NoPlayerFound extends Exception {
    public NoPlayerFound(Player player) {
        super("Player " + player.getName() + " is not found.");
    }
}
