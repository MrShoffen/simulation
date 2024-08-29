package game;

import game.actions.Action;
import game.actions.MoveAction;
import game.actions.StarveAction;
import game.actions.breed.BreedAction;
import game.actions.spawn.*;
import view.MapRenderer;
import world.GridMap;
import world.entities.Entity;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Simulation {
    private final GridMap map;

    private final MapRenderer renderer;

    private final ArrayList<Action> actions;

    boolean isAutoRunning;

    int simulationCount;

    private int millisPauseBetweenRender;

    public void setMillisPause(int millisPauseBetweenRender) {
        this.millisPauseBetweenRender = millisPauseBetweenRender;
    }

    public MapRenderer getRenderer() {
        return renderer;
    }

    public Simulation(GridMap map, MapRenderer renderer) throws InterruptedException {
        this.map = map;
        this.renderer = renderer;
        actions = new ArrayList<>();

        isAutoRunning = false;
        simulationCount = 0;
        millisPauseBetweenRender = 250;

        initActions();
        performAllActions();
        renderer.render(map);
    }

    public int getSimulationCount() {
        return simulationCount;
    }

    synchronized public boolean isAutoRunning() {
        return isAutoRunning;
    }

    public void resume() {
        isAutoRunning = true;
    }

    public void pause() {
        isAutoRunning = false;
    }

    private void initActions() {
        actions.add(new RockSpawnAction(map));
        actions.add(new GrassSpawnAction(map));
        actions.add(new TreeSpawnAction(map));
        actions.add(new HerbivoreSpawnAction(map));
        actions.add(new PredatorSpawnAction(map));
    }

    private void addActions() {
        actions.add(new MoveAction(map));
        actions.add(new GrassSpawnAction(map));
        actions.add(new BreedAction(map));
        actions.add(new StarveAction(map));
        actions.add(new HerbivoreSpawnAction(map));
    }

    private void performAllActions() throws InterruptedException {
        for (Action action : actions) {
            if (action instanceof MoveAction) {
                performMoveAction((MoveAction) action);
            } else {
                action.perform();
            }
        }
        actions.clear();
    }

    public void performMoveAction(MoveAction action) throws InterruptedException {
        while (action.moveInProgress()) {
            action.perform();
            renderer.render(map);
            Thread.sleep(millisPauseBetweenRender);
            System.out.println();
        }
    }

    public int getEntityCountByType(Class<? extends Entity> type) {
        return (int) map.allEntities().stream().filter(entity -> entity.getClass() == type).count();
    }

    public void nextTurn() throws InterruptedException {
        simulationCount++;
        addActions();
        performAllActions();
        renderer.render(map);
        Thread.sleep(millisPauseBetweenRender);
    }
}
