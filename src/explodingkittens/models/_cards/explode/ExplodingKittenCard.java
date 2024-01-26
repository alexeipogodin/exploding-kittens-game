package explodingkittens.models._cards.explode;

import explodingkittens.models._cards.std.BasicCard;

/**
 * A specific class that implements exploding kitten
 * card's functionality.
 */
public class ExplodingKittenCard extends BasicCard {
    private static final String DESCRIPTION =
            "Show this card immediately.";
    /**
     * Kicks of the specified player if he doesn't have a
     * defuse card.
     */
    public ExplodingKittenCard() {
        super();
        setDescription(DESCRIPTION);
    }
}
