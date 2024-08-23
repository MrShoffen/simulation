package game.actions;

import world.Map;
import world.entities.creatures.Creature;

import java.util.List;

public class StarveAction extends Action {

    public StarveAction(Map map) {
        super(map);
    }

    @Override
    public void perform() {

        List<Creature> allCreatures = Action.allCreaturesFromMap(map);



    }
}
