package explodingkittens._cards.std;

/**
 * An interface containing common essential functionality
 * for a card class implementation.
 */
public interface CardInterface {
    /**
     * A method without a body that obliges all of the
     * {@code CardInterface} implementations to
     * have it implemented.
     * @return {@code String} representing card's title.
     */
    String getTitle();

    /**
     * A method without a body that obliges all of the
     * {@code CardInterface} implementations to
     * have it implemented.
     * @return {@code String} representing card's description.
     */
    String getDescription();
}
