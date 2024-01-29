package explodingkittens.mvc.controller;

import explodingkittens.interfaces.ExplodingKittensUI;
import explodingkittens.mvc.model._players.ComputerPlayer;
import explodingkittens.mvc.views.ExplodingKittensTestUI;
import explodingkittens.utils.NicknameGenerator;

public class SimpleGamesHandler {
    ExplodingKittensUI ui;
    GameController controller;
    public static void main(String[] args) {
        ExplodingKittensUI ui = new ExplodingKittensTestUI();
        GameController controller = new GameController(ui);

        controller.run();
    }
}
