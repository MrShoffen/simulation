package game.actions.spawn;

import world.GridMap;
import world.entities.Entity;
import world.entities.creatures.Predator;

public final class PredatorSpawnAction extends spawnAction {
    private final static double PREDATOR_SPAWN_RATE = 0.06;

    public PredatorSpawnAction(GridMap map) {
        super(map);
        spawnRate = PREDATOR_SPAWN_RATE;
        entityTypeForSpawn = Predator.class;
    }

    @Override
    protected Entity randomEntity() {
        return Predator.randomPredator();
    }
}
