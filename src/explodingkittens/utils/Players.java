package explodingkittens.utils;

import explodingkittens.exceptions.NoPlayerFound;
import explodingkittens.mvc.model._players.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Players extends ArrayList<Player> {
    public Player next(Player player) {
        if (this.contains(player))
            return (this.getLast() == player)? this.getFirst() : this.get(this.indexOf(player) + 1);
        else
            return null;
    }
    public Player previous(Player player) {
        if (this.contains(player))
            return (this.getFirst() == player)? this.getLast() : this.get(this.indexOf(player) - 1);
        else
            return null;
    }

    public Player get(String name) {
        for (Player player: this)
            if (Objects.equals(player.getName(), name))
                return player;
        return this.get(0);
    }

    public Player random() {
        return this.get(new Random().nextInt(this.size()));
    }
}
