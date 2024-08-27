package game.actions;

import world.Cell;
import world.GridMap;
import world.entities.creatures.Creature;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class Action {

    protected final GridMap map;

    protected Action(GridMap map) {
        this.map = map;
    }

    public abstract void perform();

    protected static List<Creature> allCreaturesFromMap(GridMap map) {
        return map.allEntities().stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity).collect(Collectors.toList());
    }

    protected static Cell randomEmptyCell(GridMap map) {
        Cell result;
        Random random = new Random();
        do {
            int x = random.nextInt(map.getWidth());
            int y = random.nextInt(map.getHeight());
            result = map.getCellAt(x, y);

        } while (result.hasEntity());

        return result;
    }

}
