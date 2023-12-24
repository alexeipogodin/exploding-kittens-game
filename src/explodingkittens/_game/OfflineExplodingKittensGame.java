package explodingkittens._game;

import explodingkittens._players.HumanPlayer;

/**
 * Implements specific offline game functionality.
 */
public class OfflineExplodingKittensGame extends Game {
    /**
     * Constructor containing specific offline game logic.
     * @param playersNames playersName stands for the array of player's names.
     */
    public OfflineExplodingKittensGame(String[] playersNames) {
        super(playersNames);
    }
    /**
     * Constructor containing specific offline game logic.
     * @param playersCount playersCount stands for the number of players the created
     * game is supposed to have.
     */
    public OfflineExplodingKittensGame(int playersCount) {
        super(playersCount);
    }

    @Override
    public void addPlayer(String name) {
        getPlayers().add(new HumanPlayer(name));
    }

    /**
     * Offline specific implementation.
     */
    @Override
    public void matchPlayersUp() {

    }

    /**
     * Offline specific implementation.
     */
    @Override
    public void startUp() {
    }
}
