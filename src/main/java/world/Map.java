package world;

import world.entities.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Map {
    private final int height;
    private final int width;
    private final ArrayList<Cell> cells;

    public Map(int height, int width) {
        this.height = height;
        this.width = width;
        cells = Cell.createGridGraph(height, width);


        //todo mb peredelat d mapu?
        java.util.Map<Cell,Entity> mm = new HashMap<>();



//        getCellAt(2, 4).log();
//        getCellAt(2, 2).setEntity(new Entity());
//        getCellAt(2, 4).log();

//        List<Entity> a = allEntities();
//        System.out.println(a.size());

//        getCellAt(2, 2).removeEntity();
//        getCellAt(2, 4).log();

//        System.out.println(allEntities().size());

    }

    public Cell getCellAt(int x, int y) {
        return cells.get(y * width + x);

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Entity> allEntities() {
        return cells.stream().filter(Cell::hasEntity)
                .map(Cell::getEntity)
                .collect(Collectors.toList());
    }


    public Stream<Entity> allEntitiesStream() {
        return cells.stream().filter(Cell::hasEntity)
                .map(Cell::getEntity);
    }

    public void setEntity(Entity entity, int x, int y) {
        getCellAt(x, y).setEntity(entity);
    }

    public void removeEntity(int x, int y) {
        getCellAt(x, y).removeEntity();
    }

    public void moveEntity(int x1, int y1, int x2, int y2) {
        getCellAt(x2, y2).setEntity(getCellAt(x1, y1).getEntity());
        getCellAt(x1, y1).removeEntity();
    }

    public boolean contains(Cell cell) {
        return cells.contains(cell);
    }

    //test
    public void clearMap(){
        cells.forEach(Cell::removeEntity);
    }
}
