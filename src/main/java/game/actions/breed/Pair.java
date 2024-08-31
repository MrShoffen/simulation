//package game.actions.breed;
//
//import world.Cell;
//import world.entities.creatures.Creature;
//import world.entities.creatures.Predator;
//
//import java.util.Objects;
//import java.util.Random;
//
//abstract class Pair {
//
//    protected Cell firstParentCell;
//    protected Cell secondParentCell;
//
//    protected double chanceOfThisCreatureTypeToBreed;
//    private final double chanceOfThisPairToBreed;
//
//    protected Pair(Cell firstParent, Cell secondParent) {
//        this.firstParentCell = firstParent;
//        this.secondParentCell = secondParent;
//        chanceOfThisPairToBreed = new Random().nextDouble();
//    }
//
//    boolean canBreed() {
//        return chanceOfThisPairToBreed <= chanceOfThisCreatureTypeToBreed;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Pair pair = (Pair) o;
//        return pair.firstParentCell.equals(firstParentCell) && pair.secondParentCell.equals(secondParentCell)
//                || pair.firstParentCell.equals(secondParentCell) && pair.secondParentCell.equals(firstParentCell);
//    }
//
//    @Override
//    public int hashCode() {
//        return Math.min(Objects.hash(firstParentCell, secondParentCell), Objects.hash(secondParentCell, firstParentCell));
//    }
//
//    static Pair createCreaturePair(Cell firstParent, Cell secondParent) {
//        if (firstParent.getEntity().getClass() == Predator.class) {
//            return new PredatorPair(firstParent, secondParent);
//        } else {
//            return new HerbivorePair(firstParent, secondParent);
//        }
//    }
//
//    abstract Creature breedNewCreature();
//}
