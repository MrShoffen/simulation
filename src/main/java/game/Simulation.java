package game;

import game.actions.Action;
import game.actions.MoveAction;
import game.actions.SpawnAction;
import game.actions.StarveAction;
import view.MapRenderer;
import view.SimulationController;
import world.GridMap;
import world.entities.Entity;

import java.util.ArrayList;

public class Simulation {
    private static final int DEFAULT_PAUSE_BETWEEN_MOVES = 250;

    private final GridMap map;
    private final MapRenderer renderer;
    private final ArrayList<Action> actions;

    int simulationCount;

    public void setMillisPauseBetweenMoves(int millisPauseBetweenMoves) {
        this.millisPauseBetweenMoves = millisPauseBetweenMoves;
    }

    int millisPauseBetweenMoves;

    public Simulation(GridMap map, MapRenderer renderer) {
        this.map = map;
        this.renderer = renderer;
        actions = new ArrayList<>();

        simulationCount = 0;
        millisPauseBetweenMoves = DEFAULT_PAUSE_BETWEEN_MOVES;

        initActions();
        performAllActions();
        renderer.render(map);
    }

    public MapRenderer getRenderer() {
        return renderer;
    }

    public int getSimulationCount() {
        return simulationCount;
    }

    private void initActions() {
        actions.add(new SpawnAction(map, SpawnAction.TypeForSpawn.ROCK));
        actions.add(new SpawnAction(map, SpawnAction.TypeForSpawn.GRASS));
        actions.add(new SpawnAction(map, SpawnAction.TypeForSpawn.TREE));
        actions.add(new SpawnAction(map, SpawnAction.TypeForSpawn.HERBIVORE));
        actions.add(new SpawnAction(map, SpawnAction.TypeForSpawn.PREDATOR));
    }

    private void addActions() {
        actions.add(new MoveAction(map));
        actions.add(new SpawnAction(map, SpawnAction.TypeForSpawn.GRASS));

//        actions.add(new BreedAction(map));
        actions.add(new StarveAction(map));
        actions.add(new SpawnAction(map, SpawnAction.TypeForSpawn.HERBIVORE));

    }

    private void performAllActions() {
        for (Action action : actions) {
            if (action instanceof MoveAction) {
                performMoveAction((MoveAction) action);
            } else {
                action.perform();
            }
        }
        actions.clear();
    }

    public void performMoveAction(MoveAction action) {
        while (action.moveInProgress()) {
            action.perform();
            renderer.render(map);
            SimulationController.delay(millisPauseBetweenMoves);
            System.out.println();
        }
    }

    public int getEntityCountByType(Class<? extends Entity> type) {
        return (int) map.allEntities().stream().filter(entity -> entity.getClass() == type).count();
    }


    public void nextTurn() {
        simulationCount++;

        addActions();
        performAllActions();

        renderer.render(map);

    }
}
