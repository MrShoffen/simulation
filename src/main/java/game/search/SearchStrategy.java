package game.search;

import world.Cell;
import world.GridMap;
import world.entities.Consumable;

public interface SearchStrategy {
    Cell findNextCellToTarget(Cell startCell, GridMap map, Class<? extends Consumable> whatToFind);
}
