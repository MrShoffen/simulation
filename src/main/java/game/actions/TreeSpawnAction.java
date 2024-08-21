package game.actions;

import world.Map;
import world.entities.Entity;
import world.entities.objects.Rock;
import world.entities.objects.Tree;

public class TreeSpawnAction extends spawnAction{
    private final double TREE_RATE = 0.1;

    public TreeSpawnAction(Map map) {
        super(map);
        spawnRate = TREE_RATE;
    }

    @Override
    protected int getNumberOfEntitiesByType() {
        return getNumberOfEntitiesByType(Tree.class);
    }



    @Override
    protected Entity randomEntity() {
        return new Tree();
    }
}
