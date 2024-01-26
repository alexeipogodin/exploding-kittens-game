package explodingkittens.models._cards.cats;

import explodingkittens.models._cards.std.BasicCard;

import java.util.HashMap;

/**
 * Implements all the simple cat cards
 * and their specific functionality.
 */
public abstract class CatCard extends BasicCard {
    public static final HashMap<String, String> DESCRIPTIONS  = new HashMap<String, String>() {{
        put("BeardCat", "beard cat");
        put("Cattermelon", "cattermelon");
        put("HairyPotatoCat", "hairy potato cat");
        put("RainbowRalphingCat", "rainbow-ralphing cat");
        put("TacoCat", "tacocats");
    }};

    public CatCard() {
        super();
        setDescription((DESCRIPTIONS.get(getClass().getSimpleName()) != null) ?
                "This is a cat card and is powerless on its own." +
                "Play two " + DESCRIPTIONS.get(getClass().getSimpleName()) +
                "s as a pair to steal a random card from another player."
                : "Unspecified name.");
    }
}
