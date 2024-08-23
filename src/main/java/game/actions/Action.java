package game.actions;

import world.Map;

public abstract class Action{

    protected final Map map;

    protected Action(Map map) {
        this.map = map;
    }

    public abstract void perform();
}
