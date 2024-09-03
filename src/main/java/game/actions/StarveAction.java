package game.actions;

import world.entities.creatures.Creature;
import world.map.GridMap;

import java.util.List;

public final class StarveAction extends Action {
    private static final int MOVE_NUMBER_FOR_STARVING = 4;
    private static final int DAMAGE_FOR_STARVING = 2;

    public StarveAction(GridMap map) {
        super(map);
    }

    @Override
    public void perform() {
        List<Creature> allCreatures = ActionUtils.allCreaturesFromMap(map);

        for (Creature creature : allCreatures) {
            if (creature.movesWithoutFood() >= MOVE_NUMBER_FOR_STARVING) {
                creature.receiveDamage(DAMAGE_FOR_STARVING);
            }
            if (creature.isDead()) {
                map.removeEntity(creature);
            }
        }
    }
}
