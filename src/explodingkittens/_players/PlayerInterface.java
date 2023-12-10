package explodingkittens._players;

import explodingkittens._cards.std.BasicCard;

/**
 * An interface specifying all the methods shared by
 * both human and computer player.
 */
public interface PlayerInterface {
    void makeTurn();
    void playCard(BasicCard card);
}
