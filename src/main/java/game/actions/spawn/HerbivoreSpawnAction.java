package game.actions.spawn;

import world.Map;
import world.entities.Entity;
import world.entities.creatures.Herbivore;

public final class HerbivoreSpawnAction extends spawnAction{
    private final static double HERBIVORE_RATE = 0.1;

    public HerbivoreSpawnAction(Map map) {
        super(map);
        spawnRate = HERBIVORE_RATE + Math.random()*HERBIVORE_RATE;
        entityTypeForSpawn = Herbivore.class;
    }


    @Override
    protected Entity randomEntity() {
        return Herbivore.randomHerbivore();
    }
}
