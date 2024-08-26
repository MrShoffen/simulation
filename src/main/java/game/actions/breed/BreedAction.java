package game.actions.breed;

import game.actions.Action;
import world.Cell;
import world.Map;
import world.entities.creatures.Creature;

import java.util.*;
import java.util.function.Consumer;

public final class BreedAction extends Action {
    public BreedAction(Map map) {
        super(map);
    }

    @Override
    public void perform() {
        HashSet<Pair> pairs = calculatePairs(map);
        System.out.println(pairs.size());
        pairs.forEach(Pair::breed);
    }


    private static HashSet<Pair> calculatePairs(Map map) {
        HashSet<Pair> pairs = new HashSet<>();

        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                Cell cell = map.getCellAt(x, y);
                if (cellHasCreature(cell) && neighbourCellWithSameCreature(cell) != null) {
//                    if (creaturesInCellAreFullHealth(cell, neighbourCellWithSameCreature(cell))) {
                        pairs.add(Pair.createCreaturePair(cell, neighbourCellWithSameCreature(cell)));
//                    }
                }
            }
        }
        return pairs;
    }

    private static boolean creaturesInCellAreFullHealth(Cell first, Cell second) {
        Creature firstCreature = (Creature) first.getEntity();
        Creature secondCreature = (Creature) second.getEntity();
        return firstCreature.isFullHealed() && secondCreature.isFullHealed();
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
