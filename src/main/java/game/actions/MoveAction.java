package game.actions;

import world.Map;
import world.entities.creatures.Creature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class MoveAction extends Action {
    boolean moveInProgress;

    ArrayList<Creature> creaturesWithMoves;
    int currentStep;
    public MoveAction(Map map) {
        super(map);
        moveInProgress = true;

        creaturesWithMoves = getCreaturesWithMoves();
        currentStep = 1;

    }

    @Override
    public void perform() {
            removeDeadCreatures(creaturesWithMoves);
            removeWithNoSPeedCreatures(creaturesWithMoves,currentStep);
        //хищник убивает свинью. она пытается найтись в Мапе, но её там нет. поэтому нулл экспешн
        if(creaturesWithMoves.isEmpty()){
            moveInProgress = false;
            return;
        }

            Collections.shuffle(creaturesWithMoves); //optional
            creaturesWithMoves.forEach(creature -> creature.move(map));
            currentStep++;
    }

    public boolean moveInProgress() {
        return moveInProgress;
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
