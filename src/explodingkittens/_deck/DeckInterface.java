package explodingkittens._deck;

import explodingkittens._cards.std.BasicCard;

public interface DeckInterface {
    void addCard(BasicCard card);
    boolean removeCard(BasicCard card);
    boolean removeCard();
    void shuffle();
}
