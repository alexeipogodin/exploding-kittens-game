package explodingkittens.views._ui;

import java.io.IOException;

public interface ExplodingKittensUI {

    boolean userAccept(String prompt, char accept, char deny) throws IOException;
    void startUp();

}
