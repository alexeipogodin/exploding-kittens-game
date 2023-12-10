package explodingkittens._players;

import explodingkittens._cards.std.BasicCard;


/**
 * Implements the default player functionality.
 */
public abstract class Player implements PlayerInterface, BasicCard.Owner {
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
}
