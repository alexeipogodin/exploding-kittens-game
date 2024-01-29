package explodingkittens.mvc.model._deck;

import explodingkittens.mvc.model._cards.actions.*;
import explodingkittens.mvc.model._cards.cats.*;
import explodingkittens.mvc.model._cards.std.ActionCard;
import explodingkittens.mvc.model._cards.std.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DrawPile extends Deck {
    public static final Deck DEFAULT_DECK = new Deck(new Card[]{
            /* Cat Cards */
            new TacoCard(), new TacoCard(), new TacoCard(), new TacoCard(), // 4
            new BeardCard(), new BeardCard(), new BeardCard(), new BeardCard(), // 4
            new CattermelonCard(), new CattermelonCard(), new CattermelonCard(), new CattermelonCard(), // 4
            new HairyPotatoCard(), new HairyPotatoCard(), new HairyPotatoCard(), new HairyPotatoCard(), // 4
            new RainbowRalphingCard(), new RainbowRalphingCard(), new RainbowRalphingCard(), new RainbowRalphingCard(), // 4

            /* Action Cards */
            new ShuffleCard(), new ShuffleCard(), new ShuffleCard(), new ShuffleCard(), // 4
            new FavorCard(), new FavorCard(), new FavorCard(), new FavorCard(), // 4
            new AttackCard(), new AttackCard(), new AttackCard(), new AttackCard(), // 4
            new SkipCard(), new SkipCard(), new SkipCard(), new SkipCard(), // 4
            new SeeTheFutureCard(), new SeeTheFutureCard(), new SeeTheFutureCard(),
            new SeeTheFutureCard(), new SeeTheFutureCard(), // 5
            new NopeCard(), new NopeCard(), new NopeCard(),
            new NopeCard(), new NopeCard() // 5
    });

    public DrawPile(Deck deck) {
        this.addAll(deck);
    }

    public DrawPile() {
        this(DrawPile.DEFAULT_DECK);
    }

    public Card pull() {
        Card card = getLast();
        this.remove(card);
        return card;
    }

    public void randomlyInsert(Card card) {
        add(new Random().nextInt(size()), card);
    }

    public void insertAt(Card card, int insertAt) {
        add(insertAt, card);
    }
}
