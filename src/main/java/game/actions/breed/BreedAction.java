//package game.actions.breed;
//
//import game.actions.Action;
//import world.Cell;
//import world.GridMap;
//import world.entities.creatures.Creature;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public final class BreedAction extends Action {
//    public BreedAction(GridMap map) {
//        super(map);
//    }
//
//    @Override
//    public void perform() {
//        Set<Pair> pairs = calculatePairs(map);
//        for (Pair pair : pairs) {
//            if (pair.canBreed()) {
//                Cell emptyCell = Action.randomEmptyCell(map);
//                map.placeEntity(emptyCell,pair.breedNewCreature());
//            }
//        }
//    }
//
//    private static Set<Pair> calculatePairs(GridMap map) {
//        Set<Pair> pairs = new HashSet<>();
//
//        for (int y = 0; y < map.getHeight(); y++) {
//            for (int x = 0; x < map.getWidth(); x++) {
//                Cell cell = map.getCellAt(x, y);
//                if (cellHasCreature(cell) && neighbourCellWithSameCreature(cell) != null) {
//                    Cell neighbour = neighbourCellWithSameCreature(cell);
//                    pairs.add(Pair.createCreaturePair(cell, neighbour));
//                }
//            }
//        }
//        return pairs;
//    }
//
//    private static boolean cellHasCreature(Cell cell) {
//        return cell.hasEntity() && cell.getEntity() instanceof Creature;
//    }
//
//    private static Cell neighbourCellWithSameCreature(Cell cell) {
//        for (Cell ne : cell.neighbours()) {
//            if (ne.hasEntity() && ne.getEntity().getClass() == cell.getEntity().getClass()) {
//                return ne;
//            }
//        }
//        return null;
//    }
//}
