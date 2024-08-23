package game.algoritms;

import world.Cell;
import world.entities.Entity;

public interface SearchStrategy {
    Cell find(Cell startCell, Class<? extends Entity> whatToFind);
}
