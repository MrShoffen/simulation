package game.search;

import world.Cell;
import world.entities.Consumable;
//todo rework
public interface SearchStrategy {
    Cell find(Cell startCell, Class<? extends Consumable> whatToFind);
}
