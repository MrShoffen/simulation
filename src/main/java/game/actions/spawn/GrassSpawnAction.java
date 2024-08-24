package game.actions.spawn;

import world.Map;
import world.entities.Entity;
import world.entities.objects.Grass;

public final class GrassSpawnAction extends spawnAction {
    private final static double GRASS_SPAWN_RATE = 0.1;

    public GrassSpawnAction(Map map) {
        super(map);
        spawnRate =  GRASS_SPAWN_RATE;
        entityTypeForSpawn = Grass.class;
    }


    @Override
    protected Entity randomEntity() {
        return new Grass();
    }
}
