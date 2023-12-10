package explodingkittens._game;

import explodingkittens._players.Player;

/**
 * Implements common methods for bot offline and online games.
 */
public abstract class Game implements GameInterface {
    /**
     * Standard construnctor implementation.
     * @param playersCount stands for the number of players the created
     * game is supposed to have.
     */
    Game(int playersCount) {
        matchPlayersUp();
        setUp();
        startUp();
    }

    /**
     * Sets up the game parameters, such as
     * {@code DrawDeck}, {@code PlayerDeck}, etc.
     */
    public void setUp() {

    }

    /**
     * Adds a player to the game.
     * @param player the player to be added.
     */
    public void addPlayer(Player player) {

    }

    /**
     * Kicks a player out of the game.
     * @param player the player to be kicked out of game.
     */
    public void kickPlayer(Player player) {

    }
}
