package explodingkittens.mvc.model._deck;

import explodingkittens.mvc.model._cards.std.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Deck extends ArrayList<Card> {
    public Deck(Card[] cards) {
        super(Arrays.asList(cards));
    }

    public Deck() {
        super();
    }

    public void shuffle() {
        Collections.shuffle(this);
    }

    public boolean hasCardsOfClass(Class c) {
        for (Card card : this) {
            if (c.isInstance(card)) {
                return true;
            }
        }
        return false;
    }

    public String toJson() {
        StringBuilder json = new StringBuilder("\"" + getClass().getSimpleName().toLowerCase() + "\":{");
        json.append("\"cards\":[");
        for (Card card: this)
            json.append(card.toJson()).append(",");
        json.deleteCharAt(json.lastIndexOf(","));
        json.append("]");
        json.append("}");
        return json.toString();
    }
}
