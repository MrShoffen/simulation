package game.actions;

import view.MapRenderer;
import world.Map;
import world.entities.creatures.Creature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class MoveAction extends Action {


    public MoveAction(Map map) {
        super(map);
    }

    @Override
    public void perform() {

        ArrayList<Creature> creaturesWithMoves = getCreaturesWithMoves();

        int currentStep = 1;

        while(!creaturesWithMoves.isEmpty()){
            System.out.println("STEP " + currentStep);
            removeDeadCreatures(creaturesWithMoves);
            removeWithNoSPeedCreatures(creaturesWithMoves,currentStep);

            Collections.shuffle(creaturesWithMoves); //optional
            creaturesWithMoves.forEach(creature -> creature.move(map));
            currentStep++;

        }
    }

    private ArrayList<Creature> getCreaturesWithMoves() {
        return map.allEntities().stream()
                .filter(entity1 -> entity1 instanceof Creature)
                .map(entity1 -> (Creature) entity1)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    void removeDeadCreatures(ArrayList<Creature> list){
        list.removeIf(Creature::isDead);
    }

    void removeWithNoSPeedCreatures(ArrayList<Creature> list, int step){
        list.removeIf(creature -> creature.speed() < step);
    }
}
