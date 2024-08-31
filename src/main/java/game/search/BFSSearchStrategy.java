package game.search;

import world.Cell;
import world.GridMap;
import world.entities.Consumable;

import java.util.*;

public final class BFSSearchStrategy implements SearchStrategy {
    GridMap map;

    @Override
    public Cell find(Cell startCell, GridMap map, Class<? extends Consumable> target) {
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
                    if (CellHasNoTarget(target, neighbour)) {
                        visited.add(neighbour);
                    } else {
                        queue.add(neighbour);
                        route.put(neighbour, currentCell);
                    }
                }
            }

            if (cellHasTarget(target, currentCell)) {
//                System.out.println("found! " + currentCell + target);
                return firstCellInRoute(route, currentCell, startCell);
            }
        }
        return startCell;
    }

    private List<Cell> neighbours(Cell cell) {
        List<Cell> neighbours = new ArrayList<>();
        int x = cell.getX();
        int y = cell.getY();

        if (x != 0)
            neighbours.add(new Cell(x - 1, y));
        if (x != map.getWidth() - 1)
            neighbours.add(new Cell(x + 1, y));
        if (y != 0)
            neighbours.add(new Cell(x, y - 1));
        if (y != map.getHeight() - 1)
            neighbours.add(new Cell(x, y + 1));

//        System.out.println("Current cell: " + cell);
//        neighbours.forEach(System.out::print);
//        System.out.println();

        return neighbours;
    }

    private boolean CellHasNoTarget(Class<? extends Consumable> target, Cell neighbour) {
        return map.cellIsBusy(neighbour) && map.getEntity(neighbour).getClass() != target;
    }

    private boolean cellHasTarget(Class<? extends Consumable> target, Cell cell) {
        return map.cellIsBusy(cell) && map.getEntity(cell).getClass() == target;
    }

    private static Cell firstCellInRoute(Map<Cell, Cell> route, Cell targetCell, Cell startCell) {
        Cell prevCell = targetCell;
        while (prevCell != null) {
            if(route.get(prevCell).equals(startCell)){
                break;
            }
            prevCell = route.get(prevCell);
        }
        return prevCell;
    }
}

