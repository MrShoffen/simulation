package game;

import game.actions.*;
import game.actions.Action;
import view.MapRenderer;
import world.Map;

import java.util.ArrayList;

public class Simulation {
    final private Map map;
    final private MapRenderer renderer;

    ArrayList<Action> actions;

    boolean isRunning;

    public boolean isRunning() {
        return isRunning;
    }

    public Simulation(Map map, MapRenderer renderer) {
        this.map = map;
        this.renderer = renderer;
        actions = new ArrayList<>();

        init();

        renderer.render();
        isRunning = false;

    }

    public void resume() {
        isRunning = true;
    }

    public void pause() {
        isRunning = false;
    }

    private void generateActions() {

    }

    private void init() {
        actions.add(new RockSpawnAction(map));
        actions.add(new GrassSpawnAction(map));
        actions.add(new TreeSpawnAction(map));
        actions.add(new HerbivoreSpawnAction(map));
//        actions.add(new PredatorSpawnAction(map));



        performAllActions();

    }

    private void addActions(){
        actions.add(new MoveAction(map));
    }

    private void performAllActions() {
        actions.forEach(Action::perform);
        actions.clear();
    }



    public void nextTurn() {
        addActions();
        performAllActions();
        renderer.render();
    }
}
