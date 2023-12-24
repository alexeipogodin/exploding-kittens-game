package explodingkittens._players;

import explodingkittens._cards.std.BasicCard;
import explodingkittens._deck.PlayerDeck;


/**
 * Implements the default player functionality.
 */
public abstract class Player implements PlayerInterface, BasicCard.Owner {
    private final String name;
    private PlayerDeck hand;

    public Player(String name) {
        this.name = name;
        this.hand = new PlayerDeck();
    }

    @Override
    public String getName() {
        return name;
    }

    public PlayerDeck getHand() {
        return hand;
    }
    /**
     * Allows player to make a turn when the time comes.
     */
    @Override
    public void makeTurn() {

    }

    /**
     * Allows player to draw the card from the top of the draw pile.
     * @return The card that person drew card.
     */
    public BasicCard drawFromPile() {
        return null;
    }

    /**
     * Plays a specific card from players hand to the discard pile.
     * @param card card to be played.
     */
    public void playCard(BasicCard card) {

    }

    public void addCardToHand(BasicCard card) {
        hand.addCard(card);
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
