package explodingkittens.mvc.model._cards.std;

import explodingkittens.mvc.model._cards.cats.CatCard;
import explodingkittens.mvc.model._cards.defuse.DefuseCard;
import explodingkittens.mvc.model._cards.explode.ExplodingKittenCard;

import static explodingkittens.mvc.model._cards.std.CardsData.*;

/**
 * An abstract class for all the cards' implementations.
 */
public abstract class Card {
    /**
     * An instance variable holding card's title value.
     */
    private final String title;

    /**
     * An instance variable holding card's description value.
     */
    private final String description;

    /**
     * Constructor containing common functionality for all
     * the cards.
     */
    public Card() {
        this.title = CardsData.getCardClassTitle(this);
        this.description = CardsData.getCardClassDescription(this);
    }

    /**
     * Specific method for all cards that lets
     * developers retrieve {@code title} instance variable value.
     * @return card's {@code title} instance variable.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Specific method for all cards that lets
     * developers retrieve {@code description} instance variable value.
     * @return card's {@code description} instance variable.
     */
    public String getDescription() {
        return description;
    }

    public String toJson() {
        StringBuilder json = new StringBuilder("{\"" + getTitle() + "\":{");
        json.append("\"description\":").append("\"" + description + "\"").append("}}");
        return json.toString();
    }
}
