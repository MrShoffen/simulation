package game.actions;

import world.Map;
import world.entities.Entity;
import world.entities.objects.Grass;
import world.entities.objects.Tree;

public class TreeSpawnAction extends spawnAction{
    private final static double TREE_RATE = 0.1;

    public TreeSpawnAction(Map map) {
        super(map);
        spawnRate = TREE_RATE;
    }

    @Override
    protected final int currentQuantityOfEntityType() {
        return (int) map.allEntities().stream().filter(entity -> entity.getClass() == Tree.class).count();
    }

    @Override
    protected final Entity randomEntity() {
        return new Tree();
    }
}
