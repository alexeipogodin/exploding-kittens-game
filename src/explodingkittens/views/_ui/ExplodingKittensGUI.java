package explodingkittens.views._ui;

import java.io.IOException;

public class ExplodingKittensGUI implements ExplodingKittensUI {
    @Override
    public boolean userAccept(String prompt, char accept, char deny) throws IOException {
        return false;
    }

    @Override
    public void startUp() {

    }
}
