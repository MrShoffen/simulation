package game.actions;

import world.Cell;
import world.Map;
import world.entities.creatures.Creature;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Action{

    protected final Map map;

    protected Action(Map map) {
        this.map = map;
    }

    public abstract void perform();

    protected static List<Creature> allCreaturesFromMap(Map map) {
        return map.allEntities().stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity).collect(Collectors.toList());
    }

    protected static Cell randomEmptyCell(Map map) {
        Cell result;
        do {
            int x = (int) (Math.random() * map.getWidth());
            int y = (int) (Math.random() * map.getHeight());
            result = map.getCellAt(x, y);

        } while (result.hasEntity());

        return result;
    }

}
