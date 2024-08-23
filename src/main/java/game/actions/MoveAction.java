package game.actions;

import world.Map;
import world.entities.creatures.Creature;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MoveAction extends Action {
    boolean moveInProgress;

    List<Creature> creaturesWithMoves;
    int currentStep;

    public MoveAction(Map map) {
        super(map);
        moveInProgress = true;
        currentStep = 1;
        creaturesWithMoves = Action.allCreaturesFromMap(map);
    }

    @Override
    public final void perform() {
        this.filterCreaturesWithMoves();

        if (creaturesWithMoves.isEmpty()) {
            moveInProgress = false;
            allCreaturesFromMap(map).forEach(Creature::allowToMove);
        } else {
            Collections.shuffle(creaturesWithMoves);

            creaturesWithMoves.forEach(creature -> creature.move(map));
            currentStep++;
        }
    }


    public boolean moveInProgress() {
        return moveInProgress;
    }

    private void filterCreaturesWithMoves() {
        creaturesWithMoves = creaturesWithMoves.stream().
                filter(creature -> !creature.isDead() && creature.speed() >= currentStep && creature.canMove())
                .collect(Collectors.toList());
    }
}
