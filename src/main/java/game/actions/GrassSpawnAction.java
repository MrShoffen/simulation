package game.actions;

import world.Map;
import world.entities.Entity;
import world.entities.objects.Grass;
import world.entities.objects.Rock;

public class GrassSpawnAction extends spawnAction {
    private double GRASS_RATE = 0.1;
    public GrassSpawnAction(Map map) {
        super(map);
        spawnRate = GRASS_RATE;
    }



    protected int getNumberOfEntitiesByType() {
        return getNumberOfEntitiesByType(Grass.class);
    }

    @Override
    protected Entity randomEntity() {
        return new Grass();
    }
}
