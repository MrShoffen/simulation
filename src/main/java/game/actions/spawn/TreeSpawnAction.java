package game.actions.spawn;

import world.Map;
import world.entities.Entity;
import world.entities.objects.Tree;

public final class TreeSpawnAction extends spawnAction{
    private final static double TREE_SPAWN_RATE = 0.08;

    public TreeSpawnAction(Map map) {
        super(map);
        spawnRate = TREE_SPAWN_RATE;
        entityTypeForSpawn = Tree.class;
    }


    @Override
    protected Entity randomEntity() {
        return new Tree();
    }
}
