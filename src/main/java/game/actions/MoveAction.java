package game.actions;

import world.Map;
import world.entities.creatures.Creature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class MoveAction extends Action {
    boolean moveInProgress;

    List<Creature> creaturesWithMoves;
    int currentStep;

    public MoveAction(Map map) {
        super(map);
        moveInProgress = true;
        currentStep = 1;
        creaturesWithMoves = allCreatures();
    }

    @Override
    public final void perform() {
        creaturesWithMoves = filterCreaturesWithMoves();



        if (creaturesWithMoves.isEmpty()) {
            moveInProgress = false;
            allCreatures().forEach(Creature::allowToMove);
        } else {
            Collections.shuffle(creaturesWithMoves);

//            System.out.println("now moving:" );
//            creaturesWithMoves.forEach(creature -> System.out.print( map.locateCellOfCreature(creature) + " "));
//            System.out.println();

            creaturesWithMoves.forEach(creature -> creature.move(map));
            currentStep++;
        }
    }

    private List<Creature> allCreatures() {
        return map.allEntities().stream()
                .filter(entity -> entity instanceof Creature)
                .map(entity -> (Creature) entity).collect(Collectors.toList());
    }

    public boolean moveInProgress() {
        return moveInProgress;
    }

    private List<Creature> filterCreaturesWithMoves() {
        return creaturesWithMoves.stream().
                filter(creature -> !creature.isDead() && creature.speed() >= currentStep && !creature.isMoveFinished())
                .collect(Collectors.toList());
    }
}
