package explodingkittens.models._cards.defuse;

import explodingkittens.models._cards.std.BasicCard;

/**
 * A specific class that implements defuse
 * card's functionality.
 */
public class DefuseCard extends BasicCard {
    private static final String DESCRIPTION =
            "Instead of exploding, secretly put an exploding" +
                    "kitten back into the draw pile.";
    public DefuseCard() {
        super();
        setDescription(DESCRIPTION);
    }
    /**
     * When player draws the exploding kitten card
     * the defuse card is applied using this method.
     */
    public void use() {

    }
}
