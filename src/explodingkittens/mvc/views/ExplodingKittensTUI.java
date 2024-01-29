//package explodingkittens.mvc.views;
//
//import explodingkittens.exceptions.InvalidInputException;
//import explodingkittens.exceptions.NoPlayerFound;
//import explodingkittens.interfaces.ExplodingKittensUI;
//import explodingkittens.mvc.model._cards.defuse.DefuseCard;
//import explodingkittens.mvc.model._cards.std.ActionCard;
//import explodingkittens.mvc.model._cards.std.Card;
//import explodingkittens.mvc.model._cards.std.BlankCard;
//import explodingkittens.mvc.model._game.Game;
//import explodingkittens.mvc.model._cards.actions.FavorCard;
//import explodingkittens.mvc.model._cards.explode.ExplodingKittenCard;
//import explodingkittens.mvc.model._players.Player;
//import explodingkittens.utils.CatArtsASCII;
//import explodingkittens.utils.ConsoleColors;
//import explodingkittens.utils.IOConsole;
//import explodingkittens.utils.TextWrapper;
//
//import java.io.IOException;
//import java.util.List;
//
///**
// * Text-based user interface for the Exploding Kittens game.
// */
//public class ExplodingKittensTUI implements ExplodingKittensUI {
//
//    private IOConsole gameConsole = new IOConsole();
//    private static final String MAIN_MENU = """
//            Main Menu:
//            n - (n)ew game
//            l - (l)oad a game
//            r - show (r)ules
//            x - e(x)it the game""";
//
//    private static final String NEW_GAME_MENU = """
//            New Game:
//            o - configure (o)nline game
//            f - configure o(ff)line game
//            r - show (r)ules
//            b - go (b)ack to main menu""";
//    private static final String GAME_LOBBY_MENU = """
//            Game Lobby:
//            s - (s)tart the game
//            r - show (r)ules
//            x - e(x)it this lobby""";
//
//    private static final String RULES = """
//
//            1. Place cards face down, draw one per turn.
//            2. Play cards to skip, attack, or defuse kittens.
//            3. Collect pairs to steal opponents' cards.
//            4. If you draw an Exploding Kitten, use a Defuse card or lose.
//            5. Use other cards to strategize and survive.
//            6. Last player standing wins!""";
//
//    private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command. Try again.";
//
//    /**
//     * Constructs an ExplodingKittensTUI with a specified prompt mark.
//     *
//     * @param promptMark The prompt mark to be used in the user interface.
//     */
//    public ExplodingKittensTUI(String promptMark) {
//        gameConsole = new IOConsole(promptMark);
//    }
//
//    /**
//     * Constructs an ExplodingKittensTUI with a default prompt mark (">>").
//     */
//    public ExplodingKittensTUI() {
//        gameConsole = new IOConsole();
//    }
//
//    /**
//     * Displays the main menu and starts the user interface.
//     */
//    public void startUp() {
//        mainMenu();
//    }
//
//    /**
//     * Display rules preview to the user.
//     */
//    public void rulesPreview() {
//        gameConsole.out.println(RULES);
//        gameConsole.out.print("Press ENTER to go back...");
//        gameConsole.readString();
//    }
//
//    /**
//     * Displays the game lobby menu and handles user input.
//     *
//     * @param playerCount The number of players of the current lobby.
//     */
//    public void gameLobbyMenu(int playerCount) {
//        Game game = new OfflineExplodingKittensGame(playerCount);
//
//        while (true) {
//            switch (gameConsole.readChar(GAME_LOBBY_MENU)) {
//                case 's':
//                    if (playersReady(game.getPlayers())) {
//                        gameConsole.printlnSucc("Game started!");
//                        try {
//                            gameMenu(game);
//                        } catch (NoPlayerFound noPlayerFound) {
//                            noPlayerFound.printStackTrace();
//                        }
//                        return;
//                    }
//                    break;
//                case 'r':
//                    rulesPreview();
//                    break;
//                case 'x':
//                    try {
//                        if (gameConsole.userAccept("\nDo you really want to leave this game lobby?", 'Y', 'n'))
//                            return;
//                        else break;
//                    } catch (InvalidInputException e) {
//                        gameConsole.printErr(e.getMessage());
//                    }
//                default:
//                    gameConsole.printErr(UNKNOWN_COMMAND_MESSAGE);
//            }
//        }
//    }
//
//    /**
//     * Placeholder method for the game menu.
//     *
//     * @param game The current game instance.
//     * @throws NoPlayerFound If no player is found.
//     */
//    private void gameMenu(Game game) throws NoPlayerFound {
//
//        Player player = game.getRandomPlayer();
//
//        while (!game.hasWinner()) {
//            Card chosenCard = promptCard(player);
//
//            if (chosenCard instanceof FavorCard) {
//                Player targetPlayer = promptTarget(player, game.getPlayers());
//                game.handleTurn(player, promptTarget(player, game.getPlayers()),  chosenCard);
//            } else {
//                game.handleTurn(player, chosenCard);
//            }
//
//            player = game.getPlayers().nextPlayer(player);
//        }
//    }
//
//    private Player promptTarget(Player player, List<Player> players) {
//        gameConsole.out.println();
//        printPlayers(players);
//
//        try {
//            return players.get(gameConsole.readChar(
//                    player.getName() + ", which player do you choose as a target?") - '0' - 1);
//        } catch (NumberFormatException | IndexOutOfBoundsException e) {
//            gameConsole.printlnErr("Please enter a number of a specific player.");
//        }
//        return null;
//    }
//
//    private void printPlayers(List<Player> players) {
//        for (int i = 0; i < players.size(); i++) {
//            gameConsole.out.println(ConsoleColors.CYAN_BOLD_BRIGHT
//                    + drawPlayer(players.get(i), i + 1)
//                    + ConsoleColors.RESET);
//        }
//    }
//
//    public String drawPlayer(Player player, int playerNumber) {
//        return String.format("(%d) %s has %d cards in his hand.",
//                playerNumber, player.getName(), player.getHand().getCards().size());
//    }
//
//    /**
//     * Prompt the user for their turn.
//     *
//     * @param player The current player.
//     * @return
//     */
//    private Card promptCard(Player player) {
//        gameConsole.out.println();
//        gameConsole.out.println("You have following cards: ");
//        gameConsole.out.println();
//        printCards(player.getHand().getCards());
//
//        try {
//            int userChoice = gameConsole.readChar(
//                    player.getName() + ", which card would you like to use?") - '0';
//
//            return player.getHand().getCards().get(userChoice - 1);
//        } catch (NumberFormatException | IndexOutOfBoundsException e) {
//            gameConsole.printlnErr("Please enter a number of a specific card.");
//        }
//        return null;
//    }
//
//    /**
//     * Print the cards in the player's hand.
//     *
//     * @param cards The list of cards in the player's hand.
//     */
//    private void printCards(List<Card> cards) {
//        for (int i = 0; i < cards.size(); i++) {
//            if (i > 0)
//                gameConsole.out.println();
//            gameConsole.out.printf("(%d)\n", i + 1);
//            gameConsole.out.println(drawCard(cards.get(i), i + 1));
//        }
//    }
//
//        private void printBlankCards(List<Card> cards) {
//            for (int i = 0; i < cards.size(); i++) {
//            if (i > 0)
//                gameConsole.out.println();
//            gameConsole.out.printf("(%d)\n", i + 1);
//            gameConsole.out.println(drawCard(new BlankCard(), i + 1));
//        }
//    }
//
//    /**
//     * Draw a card and return its ASCII representation.
//     *
//     * @param card       The card to draw.
//     * @param cardNumber The card number.
//     * @return The ASCII representation of the card.
//     */
//    public String drawCard(Card card, int cardNumber) {
//        int artWidth = 30;
//        StringBuilder cardArt = new StringBuilder();
//
//        cardArt.append('+').append("-".repeat(artWidth - 2)).append('+').append('\n');
//        if (!(card instanceof BlankCard)) {
//            cardArt.append(TextWrapper.softWrapTextBorders(card.getTitle(), artWidth, '|'));
//            cardArt.append('|').append(String.format("%-" + (artWidth - 2) + "s", " ")).append('|').append('\n');
//
//
//            cardArt.append(TextWrapper.textBorders(
//                    (card instanceof ActionCard) ? CatArtsASCII.ACTION_CAT :
//                            (card instanceof ExplodingKittenCard) ? CatArtsASCII.EXPLODING_CAT :
//                                    (card instanceof DefuseCard) ? CatArtsASCII.DEFUSE_CAT :
//                                            CatArtsASCII.SIMPLE_CAT
//                    , artWidth, '|'));
//
//            cardArt.append('|').append(String.format("%-" + (artWidth - 2) + "s", " ")).append('|').append('\n');
//
//            cardArt.append(TextWrapper.softWrapTextBorders(card.getDescription(), artWidth, '|'));
//        } else {
//            cardArt.append('|').append(String.format("%-" + (artWidth - 2) + "s", " ")).append('|').append('\n');
//            cardArt.append('|').append(String.format("%-" + (artWidth - 2) + "s", " ")).append('|').append('\n');
//            cardArt.append(TextWrapper.softWrapTextBorders(card.getTitle(), artWidth, '|'));
//            cardArt.append('|').append(String.format("%-" + (artWidth - 2) + "s", " ")).append('|').append('\n');
//            cardArt.append('|').append(String.format("%-" + (artWidth - 2) + "s", " ")).append('|').append('\n');
//        }
//        cardArt.append('+').append("-".repeat(artWidth - 2)).append('+');
//
//        return ConsoleColors.CYAN_BOLD_BRIGHT + cardArt.toString() + ConsoleColors.RESET;
//    }
//
//    /**
//     * Check if all players are ready to start the game.
//     *
//     * @param players The list of players.
//     * @return True if all players are ready, false otherwise.
//     */
//    public boolean playersReady(List<Player> players) {
//        gameConsole.out.println();
//        for (Player player : players) {
//            while (true) {
//                try {
//                    gameConsole.printSucc("<*> ");
//                    if (!userAccept("Are you ready, " + player.getName() + "?")) {
//                        gameConsole.out.println("\nPlayer " + player.getName() + " is not yet ready. :(");
//                        return false;
//                    }
//                    gameConsole.printlnSucc(" |");
//                } catch (InvalidInputException e) {
//                    gameConsole.printErr(e.getMessage() + "\n");
//                    continue;
//                }
//                break;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * Display the new game menu and handle user input.
//     */
//    public void newGameMenu() {
//        while (true) {
//            switch (gameConsole.readChar(NEW_GAME_MENU)) {
//                case 'o':
//                    configureOnlineGame();
//                    break;
//                case 'f':
//                    int playerCount = -1;
//                    while (playerCount == -1)
//                        playerCount = configureOfflineGame();
//                    gameConsole.out.println("\nStarting an offline game!");
//                    gameLobbyMenu(playerCount);
//                    return;
//                case 'r':
//                    rulesPreview();
//                    break;
//                case 'b':
//                    return;
//                default:
//                    gameConsole.printErr(UNKNOWN_COMMAND_MESSAGE);
//            }
//        }
//    }
//
//    /**
//     * Placeholder method for loading a game from a file.
//     */
//    public static void loadGameMenu() {
//        // To be implemented
//    }
//
//    /**
//     * Display the main menu and handle user input.
//     */
//    public void mainMenu() {
//        while (true) {
//            switch (gameConsole.readChar(MAIN_MENU)) {
//                case 'n':
//                    newGameMenu();
//                    break;
//                case 'l':
//                    loadGameMenu();
//                    break;
//                case 'r':
//                    rulesPreview();
//                    break;
//                case 'x':
//                    try {
//                        if (gameConsole.userAccept("Do you really want to leave the game?", 'Y', 'n'))
//                            return;
//                        else break;
//                    } catch (InvalidInputException e) {
//                        gameConsole.printErr(e.getMessage());
//                    }
//                default:
//                    gameConsole.printErr(UNKNOWN_COMMAND_MESSAGE);
//            }
//        }
//    }
//
//    /**
//     * Configure an offline game based on user input.
//     *
//     * @return The configured game instance.
//     */
//    public int configureOfflineGame() throws NumberFormatException {
//        String guidelineMessage = "Please enter a number between " + Game.MIN_PLAYERS +
//                " and " + Game.MAX_PLAYERS + " inclusive.";
//        try {
//            int playerCount = gameConsole.readInt("How many human players are going to play?");
//            if (playerCount < Game.MIN_PLAYERS || playerCount > Game.MAX_PLAYERS)
//                gameConsole.printErr(guidelineMessage);
//            else return playerCount;
//        } catch (NumberFormatException e) {
//            gameConsole.printErr(guidelineMessage);
//        }
//        return -1;
//    }
//
//    /**
//     * Configure an offline game based on user input.
//     *
//     * @return The configured game instance.
//     */
//    public int configureOnlineGame() throws NumberFormatException {
//        return -1;
//    }
//
//    /**
//     * Checks .
//     *
//     * @param prompt The prompt message.
//     * @param accept The character representing acceptance.
//     * @param deny   The character representing denial.
//     * @return True if the user accepts, false if the user denies.
//     * @throws IOException If an I/O error occurs.
//     */
//    public boolean userAccept(String prompt, char accept, char deny) throws IOException {
//        return gameConsole.userAccept(prompt, accept, deny);
//    }
//
//    /**
//     * Implements the userAccept method with default accept and deny characters.
//     *
//     * @param prompt The prompt message.
//     * @return True if the user accepts, false if the user denies.
//     * @throws InvalidInputException If the input is invalid.
//     */
//    public boolean userAccept(String prompt) throws InvalidInputException {
//        return gameConsole.userAccept(prompt, 'Y', 'n');
//    }
//
//    /**
//     * Main method to launch the Exploding Kittens TUI.
//     *
//     * @param args Command-line arguments.
//     */
//    public static void main(String[] args) {
//        ExplodingKittensUI ui = new ExplodingKittensTUI();
//        ui.startUp();
//    }
//}