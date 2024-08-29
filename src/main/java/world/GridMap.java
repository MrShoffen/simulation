package world;

import world.entities.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GridMap {
    private final int height;
    private final int width;
    private final List<Cell> cells;


    public GridMap(int height, int width) {
        this.height = height;
        this.width = width;
        cells = Cell.generateConnectedGridGraph(height, width);
    }

    public Cell getCellAt(int x, int y) {
        return cells.get(y * width + x);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Entity> allEntities() {
        return cells.stream().filter(Cell::hasEntity)
                .map(Cell::getEntity)
                .collect(Collectors.toList());
    }

    public Optional<Cell> locateCellOfEntity(Entity entity) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cellForCheck = getCellAt(x, y);
                if (cellForCheck.hasEntity() && cellForCheck.getEntity() == entity) {
                    return Optional.of(cellForCheck);
                }
            }
        }
        return Optional.empty();
    }

    private void moveEntity(int x1, int y1, int x2, int y2) {
        getCellAt(x2, y2).setEntity(getCellAt(x1, y1).getEntity());
        getCellAt(x1, y1).removeEntity();
    }

    public void moveEntity(Cell from, Cell to) {
        if (!from.equals(to)) {
            moveEntity(from.getX(), from.getY(), to.getX(), to.getY());
        }
    }

    public void removeEntity(Entity entity) {
        Optional<Cell> opt = locateCellOfEntity(entity);
        opt.ifPresent(Cell::removeEntity);
    }
}
