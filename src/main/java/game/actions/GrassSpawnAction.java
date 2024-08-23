package game.actions;

import world.Map;
import world.entities.Entity;
import world.entities.objects.Grass;

public class GrassSpawnAction extends spawnAction {
    private final static double GRASS_SPAWN_RATE = 0.1;

    public GrassSpawnAction(Map map) {
        super(map);
        spawnRate = GRASS_SPAWN_RATE*Math.random() + GRASS_SPAWN_RATE;
    }

    @Override
    protected final int currentQuantityOfEntityType() {
        return currentQuantityOfEntityType(Grass.class);
    }

    @Override
    protected final Entity randomEntity() {
        return new Grass();
    }
}
