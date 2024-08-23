package game;

import game.actions.*;
import game.actions.Action;
import game.actions.spawn.*;
import view.MapRenderer;
import world.Map;

import java.util.ArrayList;

public class Simulation {
    final private Map map;
    final private MapRenderer renderer;

    ArrayList<Action> actions;

    boolean isRunning;

    int simulationCount;

    public boolean isRunning() {
        return isRunning;
    }

    public Simulation(Map map, MapRenderer renderer) throws InterruptedException {
        this.map = map;
        this.renderer = renderer;
        actions = new ArrayList<>();

        init();

        renderer.render();
        isRunning = false;
        simulationCount = 0;

    }

    public void resume() {
        isRunning = true;
    }

    public void pause() {
        isRunning = false;
    }

    private void generateActions() {

    }

    private void init() throws InterruptedException {
        actions.add(new RockSpawnAction(map));
        actions.add(new GrassSpawnAction(map));
        actions.add(new TreeSpawnAction(map));
        actions.add(new HerbivoreSpawnAction(map));
        actions.add(new PredatorSpawnAction(map));
        performAllActions();
    }

    private void addActions(){
        actions.add(new MoveAction(map));
        actions.add(new GrassSpawnAction(map));
        actions.add(new HerbivoreSpawnAction(map));
    }

    private void performAllActions() throws InterruptedException {
        for(Action action : actions){
            if (action instanceof MoveAction){
                performMoveAction((MoveAction) action);
            } else{
                action.perform();
            }
        }
        actions.clear();
    }

    public void performMoveAction(MoveAction action) throws InterruptedException {

        while(action.moveInProgress()){
            action.perform();
            if (action.moveInProgress()){
                renderer.render();
                System.out.println();
                Thread.sleep(400);
            }
        }

    }


    public void nextTurn() throws InterruptedException {
        simulationCount++;
        System.out.println("Шаг симуляции: " + simulationCount);
        addActions();
        performAllActions();
        renderer.render();
    }
}
