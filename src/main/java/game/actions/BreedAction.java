package game.actions;

import world.Cell;
import world.Map;
import world.entities.Entity;
import world.entities.creatures.Creature;
import world.entities.creatures.Predator;

import java.util.*;

public class BreedAction extends Action {
    public BreedAction(Map map) {
        super(map);
    }

    @Override
    public void perform() {
        HashSet<Pair> pairs = new HashSet<>();

        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                Cell cell = map.getCellAt(x, y);
                if (cellHasCreature(cell) && cellWithSameEntity(cell) != null) {
                    pairs.add(new Pair(cell, cellWithSameEntity(cell), cell.getEntity().getClass()));
                }
            }
        }


        System.out.println(pairs.size());
    }

    private static boolean cellHasCreature(Cell cell) {
        return cell.hasEntity() && cell.getEntity() instanceof Creature;
    }

    private Cell cellWithSameEntity(Cell cell) {

        for (Cell ne : cell.neighbours()) {
            if (ne.hasEntity() && ne.getEntity().getClass() == cell.getEntity().getClass()) {
                return ne;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Cell a = new Cell(1, 4);
        Cell b = new Cell(4, 1);
        Cell c = new Cell(2, 3);
        Pair fist = new Pair(b, a, Predator.class);
        Pair second = new Pair(a, b, Predator.class);

        Pair third = new Pair(a, c, Predator.class);

        System.out.println(fist.equals(second) + " " + fist.equals(third) + "" + second.equals(third));
    }

    private static class Pair {

        Cell firstParent;
        Cell secondParent;
        Class<? extends Entity> type;

        public Pair(Cell firstParent, Cell secondParent, Class<? extends Entity> type) {
            this.firstParent = firstParent;
            this.secondParent = secondParent;
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            Pair pair = (Pair) o;
            return pair.firstParent.equals(firstParent) && pair.secondParent.equals(secondParent) || pair.firstParent.equals(secondParent) && pair.secondParent.equals(firstParent);
        }

        @Override
        public int hashCode() {
            return Math.min(Objects.hash(firstParent, secondParent), Objects.hash(secondParent, firstParent));
        }

        public HashSet<Cell> emptyCellsAround() {
            HashSet<Cell> emptyCellsAround = new HashSet<>(firstParent.neighbours());
            emptyCellsAround.addAll(secondParent.neighbours());
            emptyCellsAround.removeIf(Cell::hasEntity);

            System.out.println(emptyCellsAround.size());
            return emptyCellsAround;
        }

    }
}
