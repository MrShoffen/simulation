package game.actions;

import world.Map;
import world.entities.Entity;
import world.entities.creatures.Herbivore;
import world.entities.objects.Rock;

public class HerbivoreSpawnAction extends spawnAction{
    private final double HERBI_RATE = 0.08;
    public HerbivoreSpawnAction(Map map) {
        super(map);
        spawnRate = HERBI_RATE;
    }

    @Override
    protected int getNumberOfEntitiesByType() {
        return getNumberOfEntitiesByType(Herbivore.class);
    }

    @Override
    protected Entity randomEntity() {
        return new Herbivore();
    }
}
