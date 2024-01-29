package explodingkittens.mvc.model._players;

import explodingkittens.mvc.model._cards.actions.NopeCard;
import explodingkittens.mvc.model._cards.defuse.DefuseCard;
import explodingkittens.mvc.model._cards.std.Card;
import explodingkittens.mvc.model._deck.PlayerDeck;
import explodingkittens.mvc.model._game.Game;

import java.util.ArrayList;


/**
 * Implements the default player functionality.
 */
public abstract class Player {
    public static class Turn {
        private final Player MADE_BY;
        private final Player TARGET;
        private final Card CARD;

        public Turn(Player madeBy, Card card, Player target) {
            MADE_BY = madeBy;
            CARD = card;
            TARGET = target;
        }

        public Player getMadeBy() {
            return MADE_BY;
        }
        public Player getTarget() {
            return TARGET;
        }
        public Card getCard() {
            return CARD;
        }
    }
    private final String name;
    private PlayerDeck hand;
    private Game game;
    private ArrayList<Turn> turns;

    public Player(String name) {
        this.name = name;
        this.hand = new PlayerDeck();
    }
    public boolean hasDefuseCard() {
        return hand.hasCardsOfClass(DefuseCard.class);
    }

    public Card getNopeCard() {
        if (hand.hasCardsOfClass(NopeCard.class)) {
            for (Card card: hand) {
                if (card instanceof NopeCard) {
                    return card;
                }
            }
        }
        return null;
    }
    public Card getDefuseCard() {
        if (hand.hasCardsOfClass(DefuseCard.class)) {
            for (Card card: hand) {
                if (card instanceof DefuseCard) {
                    return card;
                }
            }
        }
        return null;
    }
    public void explode() {
        game.addLoser(this);
    }
    public boolean isStillAlive() {
        return !game.getLosers().contains(this);
    }

    public String getName() {
        return name;
    }

    public PlayerDeck getHand() {
        return hand;
    }
    public void addToHand(Card card) {
        hand.add(card);
    }
    public void removeFromHand(Card card) {
        hand.remove(card);
    }

    public ArrayList<Turn> getTurns() {
        return turns;
    }
    public void updateTurns(Turn turn) {
        turns.add(turn);
    }

    public Game getGame() {
        return game;
    }
    public void setGame(Game game) {
        this.game = game;
    }
    @Override
    public String toString() {
        return "Player " + getName();
    }

    public String toJson() {
        StringBuilder json = new StringBuilder("{\"" + getName() + "\":{");
        json.append(hand.toJson());
        json.append("}}");
        return json.toString();
    }
}
