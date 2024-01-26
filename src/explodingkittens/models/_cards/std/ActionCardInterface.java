package explodingkittens.models._cards.std;

/**
 * An interface containing common essential functionality
 * for an action card class implementation.
 */
public interface ActionCardInterface {
    /**
     * A method without a body that serves as a
     * placeholder for future action cards implementations
     * with a specific functionality and ability.
     */
    void perform();

    /**
     * A method without a body that obliges all of the
     * {@code ActionCardInterface} implementations to
     * have it implemented.
     * @return {@code String} representing card's subtitle.
     */
    String getSubtitle();
}
