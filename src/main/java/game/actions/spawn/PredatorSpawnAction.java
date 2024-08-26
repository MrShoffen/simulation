package game.actions.spawn;

import world.Map;
import world.entities.Entity;
import world.entities.creatures.Predator;

public final class PredatorSpawnAction extends spawnAction {
    private final static double PREDATOR_RATE = 0.04;

    public PredatorSpawnAction(Map map) {
        super(map);
        spawnRate = PREDATOR_RATE;
        entityTypeForSpawn = Predator.class;
    }

    @Override
    protected Entity randomEntity() {
        return Predator.randomPredator();
    }
}
