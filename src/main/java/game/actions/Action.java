package game.actions;

import world.Map;

public abstract class Action{

    protected Map map;

    public Action(Map map) {
        this.map = map;
    }

    public abstract void perform();
}
