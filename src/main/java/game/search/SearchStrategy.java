package game.search;

import world.map.Cell;
import world.map.GridMap;
import world.entities.Consumable;

public interface SearchStrategy {
    Cell findNextCellToTarget(Cell startCell, GridMap map, Class<? extends Consumable> whatToFind);
}
