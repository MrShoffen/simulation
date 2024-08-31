package game.actions.spawn;

import game.actions.Action;
import world.Cell;
import world.GridMap;
import world.entities.Entity;

public abstract class spawnAction extends Action {
    protected double spawnRate;
    protected Class<? extends Entity> entityTypeForSpawn;

    protected spawnAction(GridMap map) {
        super(map);
    }

    @Override
    public final void perform() {
        int quantityForSpawn = getSpawnQuantity();

        for (int i = 0; i < quantityForSpawn; i++) {
            Cell randomEmptyCell = Action.randomEmptyCell(map);
            map.placeEntity(randomEmptyCell,randomEntity());
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
