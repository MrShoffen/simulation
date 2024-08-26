package game.actions.spawn;

import world.Map;
import world.entities.Entity;
import world.entities.objects.Rock;

public final class RockSpawnAction extends spawnAction {
    private final static double ROCK_RATE = 0.09;

    public RockSpawnAction(Map map) {
        super(map);
        spawnRate = ROCK_RATE;
        entityTypeForSpawn = Rock.class;
    }


    @Override
    protected Entity randomEntity() {
        return new Rock();
    }

}
