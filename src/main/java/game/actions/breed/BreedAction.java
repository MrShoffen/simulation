package game.actions.breed;

import game.actions.Action;
import world.Cell;
import world.Map;
import world.entities.creatures.Creature;

import java.util.HashSet;

public final class BreedAction extends Action {
    public BreedAction(Map map) {
        super(map);
    }

    @Override
    public void perform() {
        HashSet<Pair> pairs = calculatePairs(map);
        for (Pair pair : pairs) {
            pair.breed(map);
        }
    }


    private static HashSet<Pair> calculatePairs(Map map) {
        HashSet<Pair> pairs = new HashSet<>();

        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                Cell cell = map.getCellAt(x, y);
                if (cellHasCreature(cell) && neighbourCellWithSameCreature(cell) != null) {
                    Cell neighbour = neighbourCellWithSameCreature(cell);
                    pairs.add(Pair.createCreaturePair(cell, neighbour));
                }
            }
        }
        return pairs;
    }


    private static boolean cellHasCreature(Cell cell) {
        return cell.hasEntity() && cell.getEntity() instanceof Creature;
    }

    private static Cell neighbourCellWithSameCreature(Cell cell) {
        for (Cell ne : cell.neighbours()) {
            if (ne.hasEntity() && ne.getEntity().getClass() == cell.getEntity().getClass()) {
                return ne;
            }
        }
        return null;
    }
}
