package explodingkittens._deck;

import explodingkittens._cards.std.BasicCard;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Deck implements DeckInterface {
    private ArrayList<BasicCard> cards = new ArrayList<>();

    @Override
    public void addCard(BasicCard card) {
        cards.add(card);
    }

    public void addCard(int index, BasicCard card) {
        cards.add(index, card);
    }

    public BasicCard lastCard() {
        return cards.getLast();
    }

    public ArrayList<BasicCard> getCards() {
        return cards;
    }

    @Override
    public boolean removeCard(BasicCard card) {
        return cards.remove(card);
    }

    @Override
    public boolean removeCard() {
        return removeCard(cards.getLast());
    }

    @Override
    public void shuffle() {
        Collections.shuffle(cards);
    }

    public String toJson() {
        StringBuilder json = new StringBuilder("\"" + getClass().getSimpleName().toLowerCase() + "\":{");
        json.append("\"cards\":[");
        for (BasicCard card: cards)
            json.append(card.toJson()).append(",");
        json.deleteCharAt(json.lastIndexOf(","));
        json.append("]");
        json.append("}");
        return json.toString();
    }
}
