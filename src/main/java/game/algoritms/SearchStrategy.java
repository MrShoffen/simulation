package game.algoritms;

import world.Cell;
import world.entities.Entity;

public interface SearchStrategy {
    public  Cell find(Cell startCell, Class<? extends Entity> whatToFind);
}
