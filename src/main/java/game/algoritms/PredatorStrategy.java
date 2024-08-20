package game.algoritms;

//import world.Cell;
//import world.entities.creatures.Herbivore;
// class PredatorStrategy extends Strategy {
//
//    PredatorStrategy(Cell start) {
//        super(start);
//    }
//
//    @Override
//    public Cell calculateNextCell() {
//        queue.add(startCell);
//
//        Cell tempCell = null;
//        while (!queue.isEmpty()) {
//            tempCell = queue.remove();
//            if (!visited.contains(tempCell)) {
//                visited.add(tempCell);
//                if (tempCell.getEntity() instanceof Herbivore) {
//                    System.out.println("found!");
//                    break;
//                } else {
//                    for (Cell c : tempCell.neighbours) {
//
//                        if (!c.hasEntity() || c.getEntity() instanceof Herbivore) {
//                            c.prev = tempCell;
//                            queue.add(c);
//                        } else {
//                            visited.add(c);
//                        }
//                    }
//                }
//            }
//        }
//        return tempCell;
//    }
//}
