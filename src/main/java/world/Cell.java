package world;

import world.entities.Entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Cell {
    private final int x;
    private final int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private Entity entity;

    public final ArrayList<Cell> neighbours;

    //    kostyl TODO: peredelat eto v algoritmicheskom klasse
    public Cell prev = null;


    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        neighbours = new ArrayList<>();
        entity = null;
    }

    public Entity getEntity() {
        return entity;
    }


    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    void removeEntity() {
        entity = null;

    }


    public boolean hasEntity() {
        return entity != null;
    }




    public void log() {
        System.out.println("Node (" + (x) + ", " + (y) + ") connected to ");
        for (Cell cell : neighbours) {
            System.out.print("(" + (cell.x ) + ", " + (cell.y) + ") ");
        }
        if (neighbours.isEmpty()) System.out.println("No nodes");

        class CellWithPrev {

            Cell current;
            Cell prev;

            public CellWithPrev(Cell in) {
                current = in;
            }
        }

        System.out.println();
    }


    public static ArrayList<Cell> createGridGraph(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        ArrayList<Cell> graph = new ArrayList<>();

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(x, y);
                graph.add(grid[y][x]);
            }

        createConnectionsInGraph(height, width, grid);


        //возвращаем граф-сетку, в котором каждый элемент связан с соседями
        return graph;
    }

    private static void createConnectionsInGraph(int height, int width, Cell[][] grid) {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                if (x == 0) grid[y][x].neighbours.add(grid[y][x + 1]);
                else if (x == width - 1) grid[y][x].neighbours.add(grid[y][x - 1]);
                else {
                    grid[y][x].neighbours.add(grid[y][x - 1]);
                    grid[y][x].neighbours.add(grid[y][x + 1]);
                }

                if (y == 0) grid[y][x].neighbours.add(grid[y + 1][x]);
                else if (y == height - 1) grid[y][x].neighbours.add(grid[y - 1][x]);
                else {
                    grid[y][x].neighbours.add(grid[y - 1][x]);
                    grid[y][x].neighbours.add(grid[y + 1][x]);
                }


            }
    }

    //todo nado peredelat' sistemy, chtobi zhervty bili vklyucheni v graph
    public static ArrayList<Cell> calculateRoute(Cell start, Cell end) {


        Queue<Cell> visited = new LinkedList<>();
        Queue<Cell> queue = new LinkedList<>();
        queue.add(start);


        ArrayList<Cell> route = new ArrayList<>();


        Cell tempCell = null;
        while (!queue.isEmpty()) {
            tempCell = queue.remove();
            if (!visited.contains(tempCell)) {
                visited.add(tempCell);
                if (tempCell.equals(end)) {
                    System.out.println("found!");
                    break;
                } else {
                    //tut nado v ochered dobavit tolko podhodyashie kletki
                    //esli v kletke kamen ili derevo ili creature - dobavit eto
                    //v visited
                    queue.addAll(tempCell.neighbours);

                    for (Cell c : tempCell.neighbours) {

                        if (!visited.contains(c)) c.prev = tempCell;
                    }
                }

            }


        }

        System.out.println("found and quit");

        if (tempCell != end) return new ArrayList<>();

        while (tempCell.prev != null) {
//            System.out.println(tempCell.x + " " + tempCell.y);
            route.add(tempCell);
            tempCell = tempCell.prev;
        }

        route.add(start);



        return route;
    }


    @Override
    public String toString() {
        return (x + " " + y);
    }
}
