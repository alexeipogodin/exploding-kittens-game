package explodingkittens._game;

import explodingkittens.exceptions.NoPlayerFound;
import explodingkittens.models._cards.defuse.DefuseCard;
import explodingkittens.models._cards.explode.ExplodingKittenCard;
import explodingkittens.models._cards.std.BasicCard;
import explodingkittens.models._deck.DrawPile;
import explodingkittens._players.Player;
import explodingkittens.utils.NicknameGenerator;


import java.util.*;

/**
 * Implements common methods for bot offline and online games.
 */
public abstract class Game implements GameInterface {
    public static final int MIN_PLAYERS = 2;
    public static final int MAX_PLAYERS = 5;
    private DrawPile drawPile;
    private final ArrayList<Player> players = new ArrayList<>();

    /**
     * Standard constructor implementation.
     * @param playersNames stands for the number of players the created
     * game is supposed to have.
     */
    public Game(String[] playersNames) {
        if (playersNames.length < MIN_PLAYERS)
            throw new IllegalArgumentException("The minimum number of players is " + MIN_PLAYERS);
        else if (playersNames.length > MAX_PLAYERS)
            throw new IllegalArgumentException("The maximum number of players is " + MAX_PLAYERS);

        for (String name: playersNames)
            addPlayer(name);

        setUp();
        startUp();
    }

    /**
     * Second constructor implementation which calls the main constructor
     * with a set of randomly pre-generated funny nicknames.
     * @param playersCount stands for the number of players the created
     * game is supposed to have.
     */
    public Game(int playersCount) {
        this(NicknameGenerator.generateNicknames(playersCount));
    }

    /**
     * Sets up the game parameters, such as
     * {@code DrawDeck}, {@code PlayerDeck}, etc.
     */
    public void setUp() {
        drawPile = new DrawPile();
        drawPile.shuffle();
        for (Player player: players) {
            for (int i = 0; i < 4; i++)
                player.addCardToHand(drawPile.pull());
            player.addCardToHand(new DefuseCard());
        }

        for (int i = 0; i < players.size()-1; i++)
            drawPile.insertAtRandomIndex(new ExplodingKittenCard());
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public boolean hasWinner() {
        return false;
    }

    public void handleTurn(Player player, BasicCard card) {
    }

    public void handleTurn(Player player, Player target, BasicCard card) {

    }

    public Player getRandomPlayer() {
        return players.get((new Random()).nextInt(players.size()));
    }

    /**
     * Adds a player to the game.
     * @param player the player to be added.
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getPlayer(String name) {
        for (Player player: players)
            if (Objects.equals(player.getName(), name))
                return player;
        return null;
    }

    public abstract void addPlayer(String name);

    /**
     * Kicks a player out of the game.
     * @param player the player to be kicked out of game.
     */
    public void kickPlayer(Player player) {
    }

    public String toJson() {
        StringBuilder json = new StringBuilder("\"" + getClass().getSimpleName() + "\":{");
        json.append(drawPile.toJson()).append(",");
        json.append("\"players\":[");
        for (Player player: players)
            json.append(player.toJson()).append(",");
        json.deleteCharAt(json.lastIndexOf(","));
        json.append("]}");
        return json.toString();
    }

    public Player nextPlayer(Player player) throws NoPlayerFound {
        if (players.contains(player))
            return (players.getLast() == player) ? players.getFirst() : players.get(players.indexOf(player) + 1);
        else
            throw new NoPlayerFound(player);
    }
}
