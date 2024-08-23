package game.actions.spawn;

import world.Map;
import world.entities.Entity;
import world.entities.creatures.Predator;

public final class PredatorSpawnAction extends spawnAction {
    private final static double PREDATOR_RATE = 0.06;

    public PredatorSpawnAction(Map map) {
        super(map);
        spawnRate = PREDATOR_RATE;
    }

    @Override
    protected int currentQuantityOfEntityType() {
        return (int) map.allEntities().stream().filter(entity -> entity.getClass() == Predator.class).count();
    }

    @Override
    protected Entity randomEntity() {
        return Predator.randomPredator();
    }
}
