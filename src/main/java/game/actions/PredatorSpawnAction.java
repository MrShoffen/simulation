package game.actions;

import world.Map;
import world.entities.Entity;
import world.entities.creatures.Herbivore;
import world.entities.creatures.Predator;

public class PredatorSpawnAction extends spawnAction {
    private final double PREDATOR_RATE = 0.07;
    public PredatorSpawnAction(Map map) {
        super(map);
    }

    @Override
    protected Entity randomEntity() {
        return new Predator();
    }

    @Override
    public int getSpawnQuantity() {
        int square = map.getHeight()*map.getWidth();
        int numberOfRocks = (int) (PREDATOR_RATE *square);

        return  numberOfRocks-getNumberOfEntitiesByType(Predator.class);

    }
}
