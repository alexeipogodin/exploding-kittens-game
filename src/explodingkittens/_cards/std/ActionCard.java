package explodingkittens._cards.std;

import java.util.HashMap;

/**
 * An abstract class for all the action cards' implementations.
 */
public abstract class ActionCard extends BasicCard implements ActionCardInterface {
    /**
     * Contains names of the action cards.
     */
    enum ActionCards {
    }

    /**
     * An instance variable holding a predefined set of subtitles
     * corresponding to action cards' names.
     */
    static final HashMap<ActionCards, String> SUBTITLES = new HashMap<ActionCards, String>();

    /**
     * An instance variable holding action card's subtitle value.
     */
    private String subtitle;

    /**
     * Constructor with a specific {@code ActionCard} functionality.
     */

    public ActionCard() {
        super();
        // Set a subtitle additionally
    }

    /**
     * Specific method for all action cards that lets
     * developers retrieve {@code subtitle} instance variable value.
     * @return action card's {@code subtitle} instance variable.
     */
    @Override
    public String getSubtitle() {
        return subtitle;
    }
}
