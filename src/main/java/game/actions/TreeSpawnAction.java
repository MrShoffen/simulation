package game.actions;

import world.Map;
import world.entities.Entity;
import world.entities.objects.Rock;
import world.entities.objects.Tree;

public class TreeSpawnAction extends spawnAction{

    private final double TREE_RATE = 0.1;

    public TreeSpawnAction(Map map) {
        super(map);
    }

    @Override
    public int getSpawnQuantity() {
        int mapSq = map.getHeight() * map.getWidth();
        int numberOfRocks = (int) (TREE_RATE *mapSq);

        return  numberOfRocks-getNumberOfEntitiesByType(Tree.class);
    }

    @Override
    protected Entity randomEntity() {
        return new Tree();
    }
}
