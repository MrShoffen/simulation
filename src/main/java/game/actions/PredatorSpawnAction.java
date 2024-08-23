package game.actions;

import world.Map;
import world.entities.Entity;
import world.entities.creatures.Predator;

public class PredatorSpawnAction extends spawnAction {
    private final static double PREDATOR_RATE = 0.06;

    public PredatorSpawnAction(Map map) {
        super(map);
        spawnRate = PREDATOR_RATE;
    }

    @Override
    protected final int currentQuantityOfEntityType() {
        return currentQuantityOfEntityType(Predator.class);
    }

    @Override
    protected final Entity randomEntity() {
        return Predator.randomPredator();
    }
}
