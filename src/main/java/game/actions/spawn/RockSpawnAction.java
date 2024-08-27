package game.actions.spawn;

import world.GridMap;
import world.entities.Entity;
import world.entities.objects.Rock;

public final class RockSpawnAction extends spawnAction {
    private final static double ROCK_SPAWN_RATE = 0.09;

    public RockSpawnAction(GridMap map) {
        super(map);
        spawnRate = ROCK_SPAWN_RATE;
        entityTypeForSpawn = Rock.class;
    }


    @Override
    protected Entity randomEntity() {
        return new Rock();
    }

}
