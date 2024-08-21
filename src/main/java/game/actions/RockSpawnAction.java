package game.actions;

import world.Map;
import world.entities.Entity;
import world.entities.objects.Rock;

public class RockSpawnAction extends spawnAction {

    private final double ROCK_RATE = 0.1;

    public RockSpawnAction(Map map) {
        super(map);
    }

    @Override
    protected Entity randomEntity() {
        return new Rock();
    }

    @Override
    public int getSpawnQuantity() {
        int mapSq = map.getHeight() * map.getWidth();
        int numberOfRocks = (int) (ROCK_RATE *mapSq);

        return  numberOfRocks-getNumberOfEntitiesByType(Rock.class);
    }
}
