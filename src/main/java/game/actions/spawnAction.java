package game.actions;

import world.Cell;
import world.Map;
import world.entities.Entity;

public abstract class spawnAction extends Action {
    protected double spawnRate;

    public spawnAction(Map map) {
        super(map);
    }

    @Override
    public void perform() {
        int quantityForSpawn = getSpawnQuantity();

        for (int i = 0; i < quantityForSpawn; i++) {
            getRandomEmptyCell().setEntity(randomEntity());
        }


//        place world.entities na kartu
    }

    protected int getSpawnQuantity() {
        int mapSq = map.getHeight() * map.getWidth();
        int numberToSpawn = (int) (spawnRate * mapSq);
        return numberToSpawn - getNumberOfEntitiesByType();
    }

    protected abstract int getNumberOfEntitiesByType();

    abstract protected Entity randomEntity();

    private Cell getRandomEmptyCell() {
        Cell result;
        do {
            int x = (int) (Math.random() * map.getWidth());
            int y = (int) (Math.random() * map.getHeight());
            result = map.getCellAt(x, y);

        } while (result.hasEntity());

        return result;
    }

    protected int getNumberOfEntitiesByType(Class<? extends Entity> type) {
        int result = 0;
        for (Entity entity : map.allEntities()) {
            if (entity.getClass() == type) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        spawnAction a = new PredatorSpawnAction(new Map(5, 10));
        a.getRandomEmptyCell();
    }

}
