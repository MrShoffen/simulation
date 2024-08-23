package game.actions;

import world.Cell;
import world.Map;
import world.entities.creatures.Creature;

import java.util.List;
import java.util.Optional;

public class StarveAction extends Action {
    private static final int MOVE_NUMBER_FOR_STARVING = 4;
    private static final int DAMAGE_FOR_STARVING = 2;


    public StarveAction(Map map) {
        super(map);
    }

    @Override
    public void perform() {

        List<Creature> allCreatures = Action.allCreaturesFromMap(map);

        for(Creature creature : allCreatures){
            if(creature.movesWithoutFood() >= MOVE_NUMBER_FOR_STARVING){
                creature.recieveDamage(DAMAGE_FOR_STARVING);
            }
            if(creature.isDead()){
              map.removeEntity(creature);
            }
        }




    }
}
