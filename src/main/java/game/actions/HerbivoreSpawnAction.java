package game.actions;

import world.Map;
import world.entities.Entity;
import world.entities.creatures.Herbivore;

public class HerbivoreSpawnAction extends spawnAction{
    private final static double HERBIVORE_RATE = 0.1;
    public HerbivoreSpawnAction(Map map) {
        super(map);
        spawnRate = HERBIVORE_RATE;
    }

    @Override
    protected final int currentQuantityOfEntityType() {
        return currentQuantityOfEntityType(Herbivore.class);
    }

    @Override
    protected final Entity randomEntity() {
        return Herbivore.randomHerbivore();
    }
}
