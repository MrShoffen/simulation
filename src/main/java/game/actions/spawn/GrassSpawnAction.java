package game.actions.spawn;

import world.GridMap;
import world.entities.Entity;
import world.entities.environment.Grass;

public final class GrassSpawnAction extends spawnAction {
    private final static double GRASS_SPAWN_RATE = 0.06;

    public GrassSpawnAction(GridMap map) {
        super(map);
        spawnRate =  GRASS_SPAWN_RATE;
        entityTypeForSpawn = Grass.class;
    }


    @Override
    protected Entity randomEntity() {
        return new Grass();
    }
}
