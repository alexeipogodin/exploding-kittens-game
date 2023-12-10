package explodingkittens._cards.std;

/**
 * An abstract class for all the cards' implementations.
 */
public abstract class BasicCard implements CardInterface {
    /**
     * An instance variable holding card's title value.
     */
    private String title;

    /**
     * An instance variable holding card's description value.
     */
    private String description;

    /**
     * Constructor containing common functionality for all
     * the cards.
     */

    public interface Owner {

    }

    public BasicCard() {
        // Set a subtitle additionally
    }

    /**
     * Specific method for all cards that lets
     * developers retrieve {@code title} instance variable value.
     * @return card's {@code title} instance variable.
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Specific method for all cards that lets
     * developers retrieve {@code description} instance variable value.
     * @return card's {@code description} instance variable.
     */
    @Override
    public String getDescription() {
        return description;
    }
}
