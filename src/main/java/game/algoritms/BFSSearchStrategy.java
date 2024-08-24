package game.algoritms;

import world.Cell;
import world.entities.Consumable;
import world.entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class BFSSearchStrategy implements SearchStrategy {

    @Override
    public Cell find(Cell startCell, Class<? extends Consumable> target) {
        ArrayList<Cell> visited = new ArrayList<>();
        Map<Cell, Cell> route = new HashMap<>();

        LinkedHashSet<Cell> queue = new LinkedHashSet<>();
        queue.add(startCell);

        while (!queue.isEmpty()) {
            Cell currentCell = queue.removeFirst();
            visited.add(currentCell);

            for (Cell neighbour : currentCell.neighbours()) {
                if (!visited.contains(neighbour)) {
                    if (CellHasNoTarget(target, neighbour)) {
                        visited.add(neighbour);
                    } else {
                        queue.add(neighbour);
                        route.put(neighbour, currentCell);
                    }
                }
            }

            if (cellHasTarget(target, currentCell)) {
                return firstCellInRoute(route, currentCell, startCell);
            }
        }
        return startCell;
    }

    private static boolean CellHasNoTarget(Class<? extends Consumable> target, Cell neighbour) {
        return neighbour.hasEntity() && neighbour.getEntity().getClass() != target;
    }

    private static boolean cellHasTarget(Class<? extends Consumable> target, Cell cell) {
        return cell.hasEntity() && cell.getEntity().getClass() == target;
    }

    private Cell firstCellInRoute(Map<Cell, Cell> route, Cell targetCell, Cell startCell) {
        Cell prevCell = targetCell;
        while (!startCell.neighbours().contains(prevCell)) {
            prevCell = route.get(prevCell);
        }
        return prevCell;
    }
}

