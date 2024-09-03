package game.search;

import world.entities.Entity;
import world.map.Cell;
import world.map.GridMap;

import java.util.Optional;

public interface SearchStrategy {
    Cell findNextCellToTarget(Cell startCell, GridMap map, Class<? extends Entity> whatToFind);

    static Optional<Cell> locateCellOfEntity(GridMap map, Entity entity) {

        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                Cell cell = new Cell(x, y);
                if (map.getEntity(cell).isPresent() && map.getEntity(cell).get().equals(entity)) {
                    return Optional.of(cell);
                }
            }
        }
        return Optional.empty();

    }
}
