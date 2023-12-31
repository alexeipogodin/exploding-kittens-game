package explodingkittens._game;

/**
 * Implements specific online game functionality.
 */
public class OnlineExplodingKittensGame extends Game {
    /**
     * Constructor containing specific online game logic.
     * @param playersCount playersCount stands for the number of players the created
     * game is supposed to have.
     */
    public OnlineExplodingKittensGame(String[] playersNames) {
        super(playersNames);
    }

    @Override
    public void addPlayer(String name) {

    }

    /**
     * Online specific implementation.
     */
    @Override
    public void matchPlayersUp() {

    }

    /**
     * Online specific implementation.
     */
    @Override
    public void startUp() {

    }
}
