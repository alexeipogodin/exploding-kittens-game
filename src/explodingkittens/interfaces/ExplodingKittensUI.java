package explodingkittens.interfaces;

import explodingkittens.mvc.controller.GameController;
import explodingkittens.mvc.model._cards.std.Card;
import explodingkittens.mvc.model._deck.Deck;
import explodingkittens.mvc.model._players.Player;

public interface ExplodingKittensUI {


    // Rules
    public void displayRules();

    // Main Menu
    public void displayMainMenu();

    // Game Lobby
    public void displayGameLobby();

    // In-Game UI
    public void displayExplodingKittenWarning();
    public void displayPlayerExploded();
    public void displayPlayerSurvived();
    Player.Turn promptPlayTurn();
    public Player promptTarget();
    public int promptInsertCardInDrawPile();
    public void displayCards(Deck deck);
    public void displayTopThreeCards(Deck deck);

    // Card Interactions
    public void confirmAction(String message);

    // Notifications
    public void notifyTurn(String playerName);
    public void notifyGameEvent(String event);


    // End-of-Game Screen
    public void displayWinner();


    void setController(GameController gameController);

    void announcePlayedCard(Player player, Card card);
    void announcePulledCard(Player player, Card card);

    void announcePlayer();

    Player.Turn  promptForNope(Player player);

    void announcePlayedCardNoped();
    void announcePlayerStackedNope(Player player);
}