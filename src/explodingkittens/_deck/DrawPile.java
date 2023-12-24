package explodingkittens._deck;

import explodingkittens._cards.actions.*;
import explodingkittens._cards.cats.*;
import explodingkittens._cards.defuse.DefuseCard;
import explodingkittens._cards.explode.ExplodingKittenCard;
import explodingkittens._cards.std.BasicCard;
import explodingkittens._cards.std.CardInterface;
import explodingkittens._players.Player;

import java.util.ArrayList;
import java.util.Random;

public class DrawPile extends Deck {
    public static final BasicCard[] DEFAULT_DECK = {
            /* Cat Cards */
            new TacoCat(), new TacoCat(), new TacoCat(), new TacoCat(),
            new BeardCat(), new BeardCat(), new BeardCat(), new BeardCat(),
            new Cattermelon(), new Cattermelon(), new Cattermelon(), new Cattermelon(),
            new HairyPotatoCat(), new HairyPotatoCat(), new HairyPotatoCat(), new HairyPotatoCat(),
            new RainbowRalphingCat(), new RainbowRalphingCat(), new RainbowRalphingCat(), new RainbowRalphingCat(),

            /* Action Cards */
            new ShuffleCard(), new ShuffleCard(), new ShuffleCard(), new ShuffleCard(),
            new FavorCard(), new FavorCard(), new FavorCard(), new FavorCard(),
            new AttackCard(), new AttackCard(), new AttackCard(), new AttackCard(),
            new SkipCard(), new SkipCard(), new SkipCard(), new SkipCard(),
            new SeeTheFutureCard(), new SeeTheFutureCard(), new SeeTheFutureCard(),
            new SeeTheFutureCard(), new SeeTheFutureCard(),
            new NopeCard(), new NopeCard(), new NopeCard(), new NopeCard(),
            new NopeCard(), new NopeCard()
    };

    public DrawPile(BasicCard[] cards) {
        for (BasicCard card: cards) {
            addCard(card);
        }
    }

    public DrawPile() {
        this(DrawPile.DEFAULT_DECK);
    }

    public BasicCard pull() {
        BasicCard card = lastCard();
        getCards().remove(card);
        return card;
    }

    public void insertAtRandomIndex(BasicCard card) {
        addCard(new Random().nextInt(getCards().size()), card);
    }
}
