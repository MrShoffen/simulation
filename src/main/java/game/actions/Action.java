package game.actions;

import world.map.GridMap;

public abstract class Action {
    protected final GridMap map;

    protected Action(GridMap map) {
        this.map = map;
    }

    public abstract void perform();
}
