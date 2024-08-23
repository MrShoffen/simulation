package game.actions.spawn;

import game.actions.Action;
import world.Cell;
import world.Map;
import world.entities.Entity;

public abstract class spawnAction extends Action {
    protected double spawnRate;

    protected spawnAction(Map map) {
        super(map);
    }

    @Override
    public final void perform() {
        int quantityForSpawn = getSpawnQuantity();

        for (int i = 0; i < quantityForSpawn; i++) {
            Action.randomEmptyCell(map).setEntity(randomEntity());
        }
    }

    private int getSpawnQuantity() {
        int mapSq = map.getHeight() * map.getWidth();
        int maxQuantityToSpawn = (int) (spawnRate * mapSq);
        return (maxQuantityToSpawn - currentQuantityOfEntityType());
    }

    protected abstract int currentQuantityOfEntityType();

    protected abstract Entity randomEntity();

}
