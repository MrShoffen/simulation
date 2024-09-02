package game.actions;

import world.entities.creatures.Creature;
import world.map.Cell;
import world.map.GridMap;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public final class ActionUtils {
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
}
