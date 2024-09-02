package game.actions;

import game.event.ListenerOfMapChange;
import game.event.ObservableMapChanger;
import world.map.GridMap;
import world.entities.creatures.Creature;

import java.util.Collections;
import java.util.List;

public final class MoveAction extends Action implements ObservableMapChanger {
    List<Creature> creaturesWithMoves;
    int currentStep;
    ListenerOfMapChange listener;

    public MoveAction(GridMap map) {
        super(map);
        currentStep = 1;
        creaturesWithMoves = ActionUtils.allCreaturesFromMap(map);
    }

    //todo add listener
    @Override
    public void perform() {

        while (!creaturesWithMoves.isEmpty()) {
            this.filterCreaturesWithMoves();
            Collections.shuffle(creaturesWithMoves);
            creaturesWithMoves.forEach(creature -> creature.move(map));
            currentStep++;
            notifyListener();
        }
        ActionUtils.allCreaturesFromMap(map).forEach(Creature::allowToMove);
    }


    private void filterCreaturesWithMoves() {
        creaturesWithMoves.removeIf(Creature::isDead);
        creaturesWithMoves.removeIf(creature -> creature.speed() < currentStep);
        creaturesWithMoves.removeIf(creature -> !creature.canMove());
    }

    @Override
    public void setListener(ListenerOfMapChange listener) {
        this.listener = listener;
    }

    @Override
    public void notifyListener() {
        if (listener != null) {
            listener.handleMapChange(map);
        }
    }
}
