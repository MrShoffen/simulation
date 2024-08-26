package game.actions.breed;

import game.actions.Action;
import world.Cell;
import world.Map;
import world.entities.creatures.Creature;
import world.entities.creatures.Predator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

abstract class Pair {

    protected Cell firstParentCell;
    protected Cell secondParentCell;
    private final double chanceToBreed;

    protected double breedControlChance;

    protected Pair(Cell firstParent, Cell secondParent) {
        this.firstParentCell = firstParent;
        this.secondParentCell = secondParent;
        chanceToBreed = Math.random();
    }

    void breed(Map map) {
        if (chanceToBreed > breedControlChance) {
            return;
        }
        Action.randomEmptyCell(map).setEntity(generateNewCreature());
    }

    @Override
    public boolean equals(Object o) {
        Pair pair = (Pair) o;
        return pair.firstParentCell.equals(firstParentCell) && pair.secondParentCell.equals(secondParentCell)
                || pair.firstParentCell.equals(secondParentCell) && pair.secondParentCell.equals(firstParentCell);
    }

    @Override
    public int hashCode() {
        return Math.min(Objects.hash(firstParentCell, secondParentCell), Objects.hash(secondParentCell, firstParentCell));
    }

    static Pair createCreaturePair(Cell firstParent, Cell secondParent) {
        if (firstParent.getEntity().getClass() == Predator.class) {
            return new PredatorPair(firstParent, secondParent);
        } else {
            return new HerbivorePair(firstParent, secondParent);
        }
    }

    abstract Creature generateNewCreature();
}
