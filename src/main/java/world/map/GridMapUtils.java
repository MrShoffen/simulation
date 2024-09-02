package world.map;

import world.entities.Entity;
import world.entities.creatures.Creature;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
//todo raspredelit po nuzhnim klassam
public class GridMapUtils {
    public static List<Creature> allCreaturesFromMap(GridMap map) {
        return map.allEntities().stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity).collect(Collectors.toList());
    }

    public static Cell randomEmptyCell(GridMap map) {
        Cell result;
        Random random = new Random();
        do {
            int x = random.nextInt(map.getWidth());
            int y = random.nextInt(map.getHeight());
            result = new Cell(x, y);
        } while (map.getEntity(result).isPresent());

        return result;
    }
    public static Optional<Cell> locateCellOfEntity(GridMap map, Entity entity) {
        for (Cell cell : map.cells.keySet()) {
            if (map.cells.get(cell).equals(entity)) {
                return Optional.of(cell);
            }
        }
        return Optional.empty();
    }

}
