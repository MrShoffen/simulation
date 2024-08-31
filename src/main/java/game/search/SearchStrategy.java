package game.search;

import world.Cell;
import world.GridMap;
import world.entities.Consumable;

//todo rework
public interface SearchStrategy {
    Cell find(Cell startCell, GridMap map, Class<? extends Consumable> whatToFind);
}
