package explodingkittens.mvc.model._game;

import explodingkittens.mvc.model._cards.actions.*;
import explodingkittens.mvc.model._cards.defuse.DefuseCard;
import explodingkittens.mvc.model._cards.std.ActionCard;
import explodingkittens.mvc.model._cards.std.Card;
import explodingkittens.mvc.model._deck.DrawPile;
import explodingkittens.mvc.model._deck.Deck;
import explodingkittens.mvc.model._deck.DiscardPile;
import explodingkittens.mvc.model._players.Player;
import explodingkittens.utils.Players;


import java.util.*;

/**
 * Implements common methods for bot offline and online games.
 */
public class Game {
    private Player currentPlayer;

    public void playTurn(Player.Turn turn) {
        switch (turn.getCard()) {
            case AttackCard attackCard:
                incAttacksStacked();
                incAttacksStacked();
                break;
            case NopeCard nopeCard:
                toggleNope();
                break;
            case ShuffleCard shuffleCard:
                getDrawPile().shuffle();
                break;
            default:
                break;
        }

        discardCard(turn.getMadeBy(), turn.getCard());

        turnLog.addFirst(turn);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


    public void incAttacksStacked() {
        attacksStacked++;
    }
    public void decAttacksStacked() {
        attacksStacked--;
    }
    public boolean hasAttacksInStack() {
        return !(attacksStacked == 0);
    }
    public void resetAttacksStacked() {
        attacksStacked = 0;
    }

    public void toggleNope() {
        isNoped = !isNoped;
    }
    public void resetIsNoped() {
        isNoped = false;
    }
    public boolean isNoped() {
        return isNoped;
    }

    public enum GameState {
        Created,
        Started,
        Finished
    }
    public static final int MIN_PLAYERS = 2;
    public static final int MAX_PLAYERS = 5;
    private DrawPile drawPile;
    private DiscardPile discardPile;
    private Players players;
    private ArrayList<Player.Turn> turnLog;
    private int attacksStacked;
    private boolean isNoped = false;
    private Players losers;
    private Player winner;
    private GameState gameState;

    public Game() {
        players = new Players();
        drawPile = new DrawPile();
        discardPile = new DiscardPile();
        gameState = GameState.Created;
        turnLog = new ArrayList<>();
        winner = null;
        losers = new Players();
    }

    // Getters and Setters

    public DrawPile getDrawPile() {
        return drawPile;
    }
    public void setDrawPile(DrawPile drawPile) {
        this.drawPile = drawPile;
    }
    public DiscardPile getDiscardPile() {
        return discardPile;
    }
    public void setDiscardPile(DiscardPile discardPile) {
        this.discardPile = discardPile;
    }

    public Players getPlayers() {
        return players;
    }
    public void addPlayer(Player player) {
        players.add(player);
    }

    public Players getLosers() {
        return losers;
    }
    public void addLoser(Player player) {
        players.remove(player);
        losers.add(player);
    }
    public Player getWinner() {
        return winner;
    }
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void discardCard(Player player, Card card) {
        player.removeFromHand(card);
        discardPile.add(card);
    }

    public GameState getState() {
        return gameState;
    }
    public void updateState(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean isFinished() {
        if (winner == null)
            runWinnerCheck();
        return getWinner() != null;
    }

    private void runWinnerCheck() {
        if (players.size() == 1) {
            setWinner(players.getFirst());
            updateState(GameState.Finished);
        }
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
}
