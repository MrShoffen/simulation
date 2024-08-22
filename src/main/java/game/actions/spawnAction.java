package game.actions;

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
            randomEmptyCell().setEntity(randomEntity());
        }
    }

    private int getSpawnQuantity() {
        int mapSq = map.getHeight() * map.getWidth();
        int maxQuantityToSpawn = (int) (spawnRate * mapSq);
        return (maxQuantityToSpawn - currentQuantityOfEntityType());
    }

    protected abstract int currentQuantityOfEntityType();

    protected final int currentQuantityOfEntityType(Class<? extends Entity> type) {
        return (int) map.allEntities().stream().filter(entity -> entity.getClass() == type).count();

    }

    abstract protected Entity randomEntity();

    private Cell randomEmptyCell() {
        Cell result;
        do {
            int x = (int) (Math.random() * map.getWidth());
            int y = (int) (Math.random() * map.getHeight());
            result = map.getCellAt(x, y);

        } while (result.hasEntity());

        return result;
    }


}
