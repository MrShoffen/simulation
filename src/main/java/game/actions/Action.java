package game.actions;

import world.Cell;
import world.GridMap;
import world.entities.creatures.Creature;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class Action {

    protected final GridMap map;

    protected Action(GridMap map) {
        this.map = map;
    }

    public abstract void perform();




}
