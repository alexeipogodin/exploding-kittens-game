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
        title = this.getClass().getSimpleName();
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

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public String toJson() {
        StringBuilder json = new StringBuilder("{\"" + getTitle() + "\":{");
        json.append("\"description\":").append("\"" + description + "\"").append("}}");
        return json.toString();
    }
}
