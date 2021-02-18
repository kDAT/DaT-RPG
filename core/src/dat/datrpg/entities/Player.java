package dat.datrpg.entities;

import java.io.Serializable;

public class Player implements Serializable {

    // TODO

    public String name;
    public String race;

    public Player(String name, String race) {
        this.name = name;
        this.race = race;
    }
}
