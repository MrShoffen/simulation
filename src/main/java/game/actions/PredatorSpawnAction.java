package game.actions;

import world.Map;
import world.entities.Entity;
import world.entities.creatures.Herbivore;
import world.entities.creatures.Predator;

public class PredatorSpawnAction extends spawnAction {
    private final double PREDATOR_RATE = 0.07;
    public PredatorSpawnAction(Map map) {
        super(map);
        spawnRate = PREDATOR_RATE;
    }

    @Override
    protected int getNumberOfEntitiesByType() {
        return getNumberOfEntitiesByType(Predator.class);
    }

    @Override
    protected Entity randomEntity() {
        return new Predator();
    }
}
