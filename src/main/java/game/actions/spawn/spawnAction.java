package game.actions.spawn;

import game.actions.Action;
import world.Map;
import world.entities.Entity;

public abstract class spawnAction extends Action {
    protected double spawnRate;
    protected Class<? extends Entity> entityTypeForSpawn;

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

    private int currentQuantityOfEntityType(){
        return (int) map.allEntities().stream().filter(entity -> entity.getClass() == entityTypeForSpawn).count();
    }

    protected abstract Entity randomEntity();
}
