package game.algoritms;

import world.Cell;
import world.entities.Consumable;

public interface SearchStrategy {
    Cell find(Cell startCell, Class<? extends Consumable> whatToFind);
}
