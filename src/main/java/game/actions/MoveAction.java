package game.actions;

import world.map.GridMap;
import world.entities.creatures.Creature;

import java.util.Collections;
import java.util.List;

public final class MoveAction extends Action {
    boolean moveInProgress;
    List<Creature> creaturesWithMoves;
    int currentStep;

    public MoveAction(GridMap map) {
        super(map);
        moveInProgress = true;
        currentStep = 1;
        creaturesWithMoves = ActionUtils.allCreaturesFromMap(map);
    }
//todo add listener
    @Override
    public void perform() {
        this.filterCreaturesWithMoves();

        if (creaturesWithMoves.isEmpty()) {
            moveInProgress = false;
            ActionUtils.allCreaturesFromMap(map).forEach(Creature::allowToMove);
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
        creaturesWithMoves.removeIf(Creature::isDead);
        creaturesWithMoves.removeIf(creature -> creature.speed() < currentStep);
        creaturesWithMoves.removeIf(creature -> !creature.canMove());
    }
}
