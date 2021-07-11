package dat.datrpg.entities;

import dat.datrpg.assets.Assets;

public class Player extends Entity {
    private static final long serialVersionUID = 8484116765663462528L;

    // TODO

    public Player(String name, String race) {
        super(Assets.RED, name, race, 0, 0, 0, 0);
    }

    @Override
    public void update() {
        super.update();
    }
}
