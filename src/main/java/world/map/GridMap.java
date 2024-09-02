package world.map;

import world.entities.Entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GridMap {
    private final int height;
    private final int width;
    final Map<Cell, Entity> cells;

    public GridMap(int height, int width) {
        this.height = height;
        this.width = width;
        cells = new HashMap<>();
    }

    public boolean cellIsBusy(Cell cell) {
        return cells.containsKey(cell);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Collection<Entity> allEntities() {
        return cells.values();
    }

    public void moveEntity(Cell from, Cell to) {
        if (!from.equals(to)) {
            Entity entity = getEntity(from);
            cells.remove(from);
            placeEntity(to, entity);
        }
    }

    public void placeEntity(Cell cell, Entity entity) {
        cells.put(cell, entity);
    }

    public void removeEntity(Entity entity) {
        cells.values().remove(entity);
    }

    //todo mojet vernut null
    public Entity getEntity(Cell cell) {
        return cells.get(cell);
    }


}
