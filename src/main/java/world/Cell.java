package world;

import world.entities.Entity;

import java.util.ArrayList;

public class Cell {
    private final int x;
    private final int y;

    private Entity entity;

    private final ArrayList<Cell> neighbours;


    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        neighbours = new ArrayList<>();
        entity = null;
    }

    public ArrayList<Cell> neighbours() {
        return neighbours;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    void removeEntity() {
        entity = null;
    }

    public boolean hasEntity() {
        return entity != null;
    }

    public static ArrayList<Cell> generateConnectedGridGraph(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        ArrayList<Cell> graph = new ArrayList<>();

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(x, y);
                graph.add(grid[y][x]);
            }
        createConnectionsInGraph(height, width, grid);

        return graph;
    }

    private static void createConnectionsInGraph(int height, int width, Cell[][] grid) {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                if (x == 0) {
                    grid[y][x].neighbours.add(grid[y][x + 1]);
                } else if (x == width - 1) {
                    grid[y][x].neighbours.add(grid[y][x - 1]);
                } else {
                    grid[y][x].neighbours.add(grid[y][x - 1]);
                    grid[y][x].neighbours.add(grid[y][x + 1]);
                }

                if (y == 0) {
                    grid[y][x].neighbours.add(grid[y + 1][x]);
                } else if (y == height - 1) {
                    grid[y][x].neighbours.add(grid[y - 1][x]);
                } else {
                    grid[y][x].neighbours.add(grid[y - 1][x]);
                    grid[y][x].neighbours.add(grid[y + 1][x]);
                }
            }
    }

    @Override
    public String toString() {
        return ("(" + x + " " + y + ")");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }
}
