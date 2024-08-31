package world;

import world.entities.Entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GridMap {
    private final int height;
    private final int width;
    private final Map<Cell, Entity> cells;

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
            clearCell(from);
            placeEntity(to, entity);
        }
    }

    public void placeEntity(Cell cell, Entity entity) {
        cells.put(cell, entity);
    }

    private void clearCell(Cell cell) {
        cells.remove(cell);
    }

    public void removeEntity(Entity entity) {
        cells.values().remove(entity);
    }
//todo mojet bit tut null
    public Entity getEntity(Cell cell) {
        return cells.get(cell);
    }

    public Optional<Cell> locateCellOfEntity(Entity entity) {
        for (Cell cell : cells.keySet()) {
            if (cells.get(cell).equals(entity)) {
                return Optional.of(cell);
            }
        }
        return Optional.empty();
    }
}
