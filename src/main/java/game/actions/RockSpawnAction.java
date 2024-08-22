package game.actions;

import world.Map;
import world.entities.Entity;
import world.entities.objects.Rock;

public class RockSpawnAction extends spawnAction {
    private final static double ROCK_RATE = 0.1;

    public RockSpawnAction(Map map) {
        super(map);
        spawnRate = ROCK_RATE;
    }

    @Override
    protected final int currentQuantityOfEntityType() {
        return currentQuantityOfEntityType(Rock.class);
    }

    @Override
    protected final Entity randomEntity() {
        return new Rock();
    }

}
