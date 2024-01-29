package explodingkittens.mvc.controller;

import explodingkittens.exceptions.InvalidNumberOfPlayers;
import explodingkittens.interfaces.ExplodingKittensUI;
import explodingkittens.mvc.model._cards.actions.*;
import explodingkittens.mvc.model._cards.defuse.DefuseCard;
import explodingkittens.mvc.model._cards.explode.ExplodingKittenCard;
import explodingkittens.mvc.model._cards.std.Card;
import explodingkittens.mvc.model._deck.Deck;
import explodingkittens.mvc.model._deck.DiscardPile;
import explodingkittens.mvc.model._deck.DrawPile;
import explodingkittens.mvc.model._game.Game;
import explodingkittens.mvc.model._players.ComputerPlayer;
import explodingkittens.mvc.model._players.HumanPlayer;
import explodingkittens.mvc.model._players.Player;
import explodingkittens.utils.ConsoleColors;
import explodingkittens.utils.Players;

import static explodingkittens.mvc.model._game.Game.MAX_PLAYERS;
import static explodingkittens.mvc.model._game.Game.MIN_PLAYERS;

public class GameController implements Runnable {
    Game game;
    ExplodingKittensUI ui;

    public GameController(ExplodingKittensUI ui) {
        this.game = new Game();
        this.ui = ui;
        this.ui.setController(this);
    }

    public Game getGame() {
        return game;
    }
    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }
    public void setCurrentPlayer(Player player) {
        game.setCurrentPlayer(player);
    }

    public void addPlayerToGame(Player player) {
        game.getPlayers().add(player);
        player.setGame(game);
    }
    public void removePlayerFromGame(Player player) {
        game.getPlayers().remove(player);
        player.setGame(null);
    }

    public void dealCards() {
        // Dealing each player a defuse card
        for (Player player: game.getPlayers()) {
            player.addToHand(new DefuseCard());
        }

        // Inserting the rest of the defuse cards into the draw pile
        for (int i = 0; i < DefuseCard.ORIGINAL_EK_NUMBER - game.getPlayers().size(); i++) {
            game.getDrawPile().randomlyInsert(new DefuseCard());
        }

        // Dealing each player 7 cards
        for (int i = 0; i < 7; i++) {
            for (Player player: game.getPlayers()) {
                player.addToHand(game.getDrawPile().pull());
            }
        }
    }
    public void setUp() throws InvalidNumberOfPlayers {
        if (game.getState() == Game.GameState.Created &&
                game.getPlayers().size() >= MIN_PLAYERS &&
                game.getPlayers().size() <= MAX_PLAYERS) {

            game.setDrawPile(new DrawPile());
            game.getDrawPile().shuffle();

            game.setDiscardPile(new DiscardPile());

            dealCards();

            // Insert 1 fewer than the number of players exploding kittens into the draw pile
            for (int i =0; i < game.getPlayers().size() - 1; i++) {
                game.getDrawPile().randomlyInsert(new ExplodingKittenCard());
            }
        } else {
            throw new InvalidNumberOfPlayers(game.getPlayers().size());
        }
    }

    public boolean cardKills(Card card) {
        if (card instanceof ExplodingKittenCard) {
            ui.displayExplodingKittenWarning();
            if (!game.getCurrentPlayer().hasDefuseCard()) {
                return true;
            }

            ui.displayPlayerSurvived();
            game.getDrawPile().insertAt(card, ui.promptInsertCardInDrawPile());
            game.playTurn(new Player.Turn(game.getCurrentPlayer(), game.getCurrentPlayer().getDefuseCard(), null));
        }
        return false;
    }

    public Deck getPlayableCardsDeck() {
        Deck deck = (Deck) game.getCurrentPlayer().getHand().clone();

        deck.removeIf(card -> card instanceof DefuseCard || card instanceof NopeCard);

        return deck;
    }

    private void playGame() {
        game.updateState(Game.GameState.Started);

        setCurrentPlayer(game.getPlayers().random());

        while (!game.isFinished()) {
            Player.Turn turn = null;

            ui.announcePlayer();

            if (game.hasAttacksInStack()) {
                while (game.hasAttacksInStack()) {
                    System.out.println(ConsoleColors.YELLOW + "You've got an attack in the stack." + ConsoleColors.RESET);
                    do {
                        game.resetIsNoped();
                        turn = ui.promptPlayTurn();
                        if (turn == null) {
                            break;
                        }

                        Players others = (Players) game.getPlayers().clone();
                        others.remove(game.getCurrentPlayer());

                        for (Player player: others) {
                            if (player.getHand().hasCardsOfClass(NopeCard.class)) {
                                Player.Turn nopeTurn = ui.promptForNope(player);
                                if (nopeTurn != null) {
                                    game.playTurn(nopeTurn);
                                    ui.announcePlayerStackedNope(player);
                                }
                            }
                        }

                        if (game.isNoped()) {
                            ui.announcePlayedCardNoped();
                            game.discardCard(game.getCurrentPlayer(), turn.getCard());
                            continue;
                        }

                        game.playTurn(turn);
                        ui.announcePlayedCard(turn.getMadeBy(), turn.getCard());

                        if (turn.getCard() instanceof SeeTheFutureCard) {
                            ui.displayTopThreeCards(game.getDrawPile());
                        }

                    } while (game.isNoped() || !(turn.getCard() instanceof SkipCard
                            || turn.getCard() instanceof AttackCard));

                    if (turn != null) {
                        if (turn.getCard() instanceof AttackCard)
                            break;
                        if (turn.getCard() instanceof SkipCard)
                            continue;
                    }

                    game.decAttacksStacked();

                    Card card = game.getDrawPile().pull();
                    if (cardKills(card)) {
                        Player playerToExplode = game.getCurrentPlayer();
                        ui.displayPlayerExploded();

                        System.out.println(ConsoleColors.YELLOW + "1. Changing player" + ConsoleColors.RESET);
                        setCurrentPlayer(game.getPlayers().next(getCurrentPlayer()));

                        playerToExplode.explode();

                        game.resetAttacksStacked();
                    } else {
                        game.getCurrentPlayer().addToHand(card);
                        ui.announcePulledCard(game.getCurrentPlayer(), card);
                    }
                }

                if (game.getCurrentPlayer().isStillAlive())
                    System.out.println(ConsoleColors.YELLOW + "2. Changing player" + ConsoleColors.RESET);
                    setCurrentPlayer(game.getPlayers().next(getCurrentPlayer()));
                continue;
            }


            do {
                game.resetIsNoped();
                turn = ui.promptPlayTurn();
                if (turn == null)
                    break;

                Players others = (Players) game.getPlayers().clone();
                others.remove(game.getCurrentPlayer());

                for (Player player: others) {
                    if (player.getHand().hasCardsOfClass(NopeCard.class)) {
                        Player.Turn nopeTurn = ui.promptForNope(player);
                        if (nopeTurn != null) {
                            game.playTurn(nopeTurn);
                            ui.announcePlayerStackedNope(player);
                        }
                    }
                }

                if (game.isNoped()) {
                    ui.announcePlayedCardNoped();
                    game.discardCard(game.getCurrentPlayer(), turn.getCard());
                    continue;
                }
                System.out.println("Here");

                game.playTurn(turn);
                ui.announcePlayedCard(turn.getMadeBy(), turn.getCard());

                if (turn.getCard() instanceof SeeTheFutureCard) {
                    ui.displayTopThreeCards(game.getDrawPile());
                }
            } while (game.isNoped() || !(turn.getCard() instanceof SkipCard
                    || turn.getCard() instanceof AttackCard));

            if (turn != null && (turn.getCard() instanceof SkipCard
                    || turn.getCard() instanceof AttackCard)) {
                setCurrentPlayer(game.getPlayers().next(getCurrentPlayer()));
                continue;
            }

            Card card = game.getDrawPile().pull();
            if (cardKills(card)) {
                ui.displayPlayerExploded();
                Player playerToExplode = game.getCurrentPlayer();
                setCurrentPlayer(game.getPlayers().next(getCurrentPlayer()));
                playerToExplode.explode();
            } else {
                game.getCurrentPlayer().addToHand(card);
                ui.announcePulledCard(game.getCurrentPlayer(), card);
            }

            if (game.getCurrentPlayer().isStillAlive())
                setCurrentPlayer(game.getPlayers().next(getCurrentPlayer()));
        }

        ui.displayWinner();
    }

    @Override
    public void run() {
        addPlayerToGame(new ComputerPlayer("Player 1"));
        addPlayerToGame(new ComputerPlayer("Player 2"));
        addPlayerToGame(new HumanPlayer("Player 3"));

        try {
            setUp();
        } catch (InvalidNumberOfPlayers e) {
            return;
        }

        playGame();
    }
}
