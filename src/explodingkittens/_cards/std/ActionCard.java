package explodingkittens._cards.std;

import java.util.HashMap;

/**
 * An abstract class for all the action cards' implementations.
 */
public abstract class ActionCard extends BasicCard implements ActionCardInterface {
    /**
     * A constant holding a predefined set of descriptions
     * corresponding to action cards' names.
     */
    public static final HashMap<String, String> DESCRIPTIONS  = new HashMap<String, String>() {{
        put("AttackCard", "Do not draw any cards. Force the next player to take two turns.");
        put("FavorCard", "One player must give you a card of their choice.");
        put("NopeCard", "Stop the action of another player. You can play this at any time.");
        put("SeeTheFutureCard", "Privately view the top three cards of the pile.");
        put("ShuffleCard", "Shuffle the draw pile.");
        put("SkipCard", "End your turn without drawing a card.");
    }};

    /**
     * An instance variable holding action card's subtitle value.
     */
    private String subtitle;

    /**
     * Constructor with a specific {@code ActionCard} functionality.
     */

    public ActionCard() {
        super();

        setDescription((DESCRIPTIONS.get(getClass().getSimpleName()) != null) ?
                DESCRIPTIONS.get(getClass().getSimpleName())
                : "Unspecified name.");
        subtitle = "Rub the belly of a pig-a-corn";
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

    @Override
    public String toJson() {
        StringBuilder json = new StringBuilder(
                super.toJson().substring(0, super.toJson().length() - 2)
        );
        json.append(",\"subtitle\":").append("\"").append(subtitle).append("\"").append("}}");
        return json.toString();
    }
}
