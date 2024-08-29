package game;

import game.actions.Action;
import game.actions.MoveAction;
import game.actions.StarveAction;
import game.actions.breed.BreedAction;
import game.actions.spawn.*;
import view.MapRenderer;
import world.GridMap;
import world.entities.creatures.Herbivore;
import world.entities.creatures.Predator;

import java.util.ArrayList;

public class Simulation {
    private final GridMap map;
    private final MapRenderer renderer;

    private final ArrayList<Action> actions;

    boolean isAutoRunning;

    int simulationCount;

    public Simulation(GridMap map, MapRenderer renderer) throws InterruptedException {
        this.map = map;
        this.renderer = renderer;
        actions = new ArrayList<>();

        isAutoRunning = false;
        simulationCount = 0;

        initActions();
        performAllActions();
        renderer.render();
    }

    public boolean isAutoRunning() {
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
            if (action.moveInProgress()) {
                renderer.render();
                System.out.println();
                Thread.sleep(100);
            }
        }
    }

    double h, p;

    public void nextTurn() throws InterruptedException {
        simulationCount++;
        addActions();
        performAllActions();
        renderer.render();
        Thread.sleep(50);

//        long HerbSize = map.allEntities().stream().filter(entity -> entity.getClass() == Herbivore.class).count();
//        long predator = map.allEntities().stream().filter(entity -> entity.getClass() == Predator.class).count();
//
//        double herbPerc = (double) HerbSize / (double) (predator + HerbSize);
//        h += herbPerc;
//        p += (1.0 - herbPerc);
//        if (simulationCount % 100 == 0)
//            System.out.println("turn: " + simulationCount + " Predators: " + (1-herbPerc) + ", Herbi: " + (herbPerc));
//    }
    }
}
