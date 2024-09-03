package game.search;

import world.entities.Entity;
import world.map.Cell;
import world.map.GridMap;

import java.util.*;

public final class BFSSearchStrategy implements SearchStrategy {
    GridMap map;

    @Override
    public Cell findNextCellToTarget(Cell startCell, GridMap map, Class<? extends Entity> target) {
        this.map = map;

        List<Cell> visited = new ArrayList<>();
        Map<Cell, Cell> route = new HashMap<>();

        LinkedHashSet<Cell> queue = new LinkedHashSet<>();
        queue.add(startCell);

        while (!queue.isEmpty()) {
            Cell currentCell = queue.removeFirst();
            visited.add(currentCell);

            for (Cell neighbour : neighbours(currentCell)) {
                if (!visited.contains(neighbour)) {
                    if (cellHasNoTarget(neighbour, target)) {
                        visited.add(neighbour);
                    } else {
                        queue.add(neighbour);
                        route.put(neighbour, currentCell);
                    }
                }
            }
            if (cellHasTarget(currentCell, target) && !currentCell.equals(startCell)) {
                return firstCellInRoute(route, currentCell, startCell);
            }

        }
        return startCell;
    }

    private List<Cell> neighbours(Cell cell) {
        List<Cell> neighbours = new ArrayList<>();
        int x = cell.x();
        int y = cell.y();

        if (x != 0)
            neighbours.add(new Cell(x - 1, y));
        if (x != map.getWidth() - 1)
            neighbours.add(new Cell(x + 1, y));
        if (y != 0)
            neighbours.add(new Cell(x, y - 1));
        if (y != map.getHeight() - 1)
            neighbours.add(new Cell(x, y + 1));

        return neighbours;
    }

    private boolean cellHasNoTarget(Cell neighbour, Class<? extends Entity> target) {
        return map.getEntity(neighbour).isPresent() && map.getEntity(neighbour).get().getClass() != target;
    }

    private boolean cellHasTarget(Cell cell, Class<? extends Entity> target) {
        return map.getEntity(cell).isPresent() && map.getEntity(cell).get().getClass() == target;
    }

    private static Cell firstCellInRoute(Map<Cell, Cell> route, Cell targetCell, Cell startCell) {
        Cell prevCell = targetCell;
        while (prevCell != null) {
            if (route.get(prevCell).equals(startCell)) {
                break;
            }
            prevCell = route.get(prevCell);
        }
        return prevCell;
    }
}

