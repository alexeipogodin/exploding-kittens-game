package explodingkittens.mvc.views;

import explodingkittens.interfaces.ExplodingKittensUI;
import explodingkittens.mvc.controller.GameController;
import explodingkittens.mvc.model._cards.actions.FavorCard;
import explodingkittens.mvc.model._cards.actions.NopeCard;
import explodingkittens.mvc.model._cards.defuse.DefuseCard;
import explodingkittens.mvc.model._cards.std.Card;
import explodingkittens.mvc.model._deck.Deck;
import explodingkittens.mvc.model._players.Player;
import explodingkittens.utils.ConsoleColors;
import explodingkittens.utils.Players;

import java.util.Scanner;

public class ExplodingKittensTestUI implements ExplodingKittensUI {
    GameController controller;
    Scanner in = new Scanner(System.in);

    @Override
    public void setController(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void displayRules() {
        System.out.println("""
            EXPLODING KITTENS GAME RULES:
             > Place cards face down, draw one per turn.
             > Play cards to skip, attack, or defuse kittens.
             > Collect pairs to steal opponents' cards.
             > If you draw an Exploding Kitten, use a Defuse card or lose.
             > Use other cards to strategize and survive.
             > Last player standing wins!
            
            Press ENTER to continue.""");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        scanner.close();
    }

    @Override
    public void displayMainMenu() {
        System.out.println("""
                Exploding Kittens Game
                Main Menu
                 (1) Play Game
                 (2) Game Rules
                 (3) Exit
                """);

        System.out.print("Enter your choice: ");
        int choice = in.nextInt();

        switch (choice) {
            case 1:
                ;
                break;
            case 2:
                break;
            case 3:
                return;
            default:
                displayMainMenu();
                break;
        }
    }

    @Override
    public void displayGameLobby() {
        System.out.println("""
                Exploding Kittens Game
                Game Lobby
                 (1) Start Game
                 (2) Game Rules
                 (3) Back to Main Menu
                """);

        System.out.print("Enter your choice: ");
        int choice = in.nextInt();

        switch (choice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                return;
            default:
                displayGameLobby();
                break;
        }
    }

    @Override
    public void displayExplodingKittenWarning() {
        System.out.print(ConsoleColors.YELLOW_BOLD_BRIGHT);
        System.out.println("Oops... "
                + controller.getCurrentPlayer().getName()
                + ", you've drawn an Exploding Kitten.");
        System.out.print(ConsoleColors.RESET);
    }

    @Override
    public void displayPlayerExploded() {
        System.out.print(ConsoleColors.RED_BRIGHT);
        System.out.println("Seems that "
                + controller.getCurrentPlayer().getName()
                + " didn't have a Defuse card.");
        System.out.print(ConsoleColors.RED_BOLD_BRIGHT);
        System.out.println("* KABOOM! *");
        System.out.print(ConsoleColors.RESET);
    }

    @Override
    public void displayPlayerSurvived() {
        System.out.print(ConsoleColors.GREEN_BOLD_BRIGHT);
        System.out.println("You're a lucky one! You used your Defuse card and survived!");
        System.out.print(ConsoleColors.RESET);
    }

    @Override
    public int promptInsertCardInDrawPile() {
        if (controller.getGame().getDrawPile().isEmpty()) {
            return 0;
        }

        while (true) {
            System.out.println("The draw pile currently has " + controller.getGame().getDrawPile().size() + " cards.");
            System.out.println("At which position do you want to insert the card?");
            System.out.println("(from 0 to " + controller.getGame().getDrawPile().size() + ")");

            System.out.print("Enter your choice: ");
            int choice = in.nextInt();

            if (choice >= 0 && choice <= controller.getGame().getDrawPile().size()) {
                return choice;
            }
        }
    }

    @Override
    public void confirmAction(String message) {

    }

    @Override
    public void notifyTurn(String playerName) {

    }

    @Override
    public void notifyGameEvent(String event) {

    }

    @Override
    public void displayWinner() {
        System.out.println(ConsoleColors.GREEN_BACKGROUND_BRIGHT + ConsoleColors.WHITE_BRIGHT);
        System.out.println(controller.getGame().getWinner().getName() + " has won this game!");
        System.out.println(ConsoleColors.RESET);
    }

    @Override
    public void displayCards(Deck deck) {
        int i = 1;
        for (Card card : deck) {
            System.out.println(i + ". " + card.getTitle());
            i++;
        }
        System.out.println();
    }

    @Override
    public void displayTopThreeCards(Deck deck) {
        System.out.println("Here are the top three cards:");
        for (int i = deck.size() - 1; i > deck.size() - 4; i--) {
            System.out.println(deck.indexOf(deck.get(i)) + ". " + deck.get(i).getTitle());
        }
        System.out.println();
    }


    @Override
    public Player.Turn promptPlayTurn() {
        Deck playableCardsDeck  = controller.getPlayableCardsDeck();

        while (true) {
            displayCards(playableCardsDeck);
            displayUnplayableCardsNumber();
            System.out.println(controller.getCurrentPlayer().getName() + " what card do you want to play next?");
            System.out.println("(0 if you don't want to play any cards)");
            int input = in.nextInt() - 1;
            if (input < -1 || input >= playableCardsDeck.size()) {
                System.out.println("Please enter a valid choice.");
                continue;
            }

            if (input == -1) {
                break;
            }

            if (playableCardsDeck.get(input) instanceof FavorCard) {
                return new Player.Turn(controller.getCurrentPlayer(), playableCardsDeck.get(input), promptTarget());
            }

            return new Player.Turn(controller.getCurrentPlayer(), playableCardsDeck.get(input), null);
        }

        return null;
    }

    public void displayUnplayableCardsNumber() {
        System.out.println("You also have "
                + controller.getCurrentPlayer().getHand().stream().filter(
                card -> card instanceof DefuseCard).count()
                + " defuse and "
                + controller.getCurrentPlayer().getHand().stream().filter(
                card -> card instanceof NopeCard).count()
                + " nope cards.");
    }

    @Override
    public void announcePlayedCardNoped() {
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT);
        System.out.println(controller.getCurrentPlayer() + " your turn was canceled by a nope card");
        System.out.println(ConsoleColors.RESET);
    }

    @Override
    public void announcePlayerStackedNope(Player player) {
        System.out.println(ConsoleColors.YELLOW + player.getName() + " has put a nope card" + ConsoleColors.RESET);
    }

    @Override
    public Player.Turn promptForNope(Player player) {
        while (true) {
            System.out.println(player.getName() + ", would you like to put you nope card on top?");
            System.out.print("Enter your choice (Y/n): ");
            String input = in.next();

            if (input.equalsIgnoreCase("y")) {
                return new Player.Turn(player, player.getNopeCard(), null);
            } else if (input.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Please enter a valid choice.");
            }
        }
        return null;
    }

    @Override
    public Player promptTarget() {
        Player target = null;

        Players others = (Players) controller.getGame().getPlayers().clone();
        others.remove(controller.getCurrentPlayer());

        while (target == null) {
            displayPlayers(others);
            System.out.println("Which player do you chose as the target?");
            int input = in.nextInt() - 1;

            if (input < 0 || input >= others.size()) {
                System.out.println("Please enter a valid choice.");
                continue;
            }

            target = controller.getGame().getPlayers().get(input);
        }

        return target;
    }

    public void announcePlayedCard(Player player, Card card) {
        System.out.print(ConsoleColors.PURPLE_BOLD_BRIGHT);
        System.out.println(player.getName() + " played " + card.getTitle());
        System.out.println(ConsoleColors.RESET);
    }

    public void announcePulledCard(Player player, Card card) {
        System.out.println(player.getName() + " pulled " + card.getTitle());
        System.out.println();
    }

    @Override
    public void announcePlayer() {
        System.out.print(ConsoleColors.BLUE_BOLD_BRIGHT);
        System.out.println(controller.getCurrentPlayer().getName()
                + " is making a turn.");

        System.out.print(ConsoleColors.BLUE_BRIGHT);
        System.out.println(controller.getGame().getPlayers().next(controller.getCurrentPlayer()).getName()
                + " is next to make a turn.");
        System.out.println(ConsoleColors.RESET);
    }

    private void displayPlayers(Players players) {
        int i = 1;
        for (Player player : players) {
            System.out.println(i + ". " + player.getName());
            i++;
        }
    }
}
