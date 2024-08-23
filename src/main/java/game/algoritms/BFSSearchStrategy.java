package game.algoritms;

import world.Cell;
import world.entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class BFSSearchStrategy implements SearchStrategy {

    @Override
    public Cell find(Cell startCell, Class<? extends Entity> whatToFind) {
        ArrayList<Cell> visited = new ArrayList<>();
        Map<Cell, Cell> route = new HashMap<>();

        LinkedHashSet<Cell> queue = new LinkedHashSet<>();
        queue.add(startCell);

        while (!queue.isEmpty()) {
            Cell tempCell = queue.removeFirst();
            visited.add(tempCell);

            for (Cell neighbour : tempCell.neighbours()) {
                if (!visited.contains(neighbour)) {
                    if (neighbour.hasEntity() && neighbour.getEntity().getClass() != whatToFind) {
                        visited.add(neighbour);
                    } else {
                        queue.add(neighbour);
                        route.put(neighbour, tempCell);
                    }
                }
            }

            if (tempCell.hasEntity() && tempCell.getEntity().getClass() == whatToFind) {
                return unwrapRoute(route, tempCell,startCell);
            }
        }
        return startCell;
    }

    private Cell unwrapRoute(Map<Cell, Cell> route, Cell targetCell, Cell startCell) {
        Cell prevCell = targetCell;
        while(!startCell.neighbours().contains(prevCell)){
            prevCell = route.get(prevCell);
        }
        return prevCell;
    }


}

