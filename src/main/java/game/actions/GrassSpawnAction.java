package game.actions;

import world.Map;
import world.entities.Entity;
import world.entities.objects.Grass;
import world.entities.objects.Rock;

public class GrassSpawnAction extends spawnAction {
    private double GRASS_RATE = 0.1;
    @Override
    protected Entity randomEntity() {
        return new Grass();
    }

    public GrassSpawnAction(Map map) {
        super(map);
    }

    @Override
    public int getSpawnQuantity() {
        int mapSq = map.getHeight() * map.getWidth();
        int numberOfRocks = (int) (GRASS_RATE *mapSq);

        return  numberOfRocks-getNumberOfEntitiesByType(Grass.class);
    }
}
