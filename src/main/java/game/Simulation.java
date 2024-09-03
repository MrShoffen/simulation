package game;

import game.actions.*;
import view.MapRenderer;
import world.entities.Entity;
import world.map.GridMap;

import java.util.ArrayList;

public class Simulation {
    private final GridMap map;
    private final MapRenderer renderer;
    private final ArrayList<Action> actions;

    int simulationCount;

    public Simulation(GridMap map, MapRenderer renderer) {
        this.map = map;
        this.renderer = renderer;
        actions = new ArrayList<>();

        simulationCount = 0;

        initActions();
        performAllActions();
        renderer.render(map);
    }

    public int getSimulationCount() {
        return simulationCount;
    }

    private void initActions() {
        actions.add(new SpawnAction(map, SpawnAction.EntityType.ROCK));
        actions.add(new SpawnAction(map, SpawnAction.EntityType.GRASS));
        actions.add(new SpawnAction(map, SpawnAction.EntityType.TREE));
        actions.add(new SpawnAction(map, SpawnAction.EntityType.HERBIVORE));
        actions.add(new SpawnAction(map, SpawnAction.EntityType.PREDATOR));
    }

    private void addActions() {
        MoveAction move = new MoveAction(map);
        move.setListener(renderer);
        actions.add(move);

        actions.add(new SpawnAction(map, SpawnAction.EntityType.GRASS));
        actions.add(new SpawnAction(map, SpawnAction.EntityType.HERBIVORE));
        actions.add(new BreedAction(map));
        actions.add(new StarveAction(map));
    }

    private void performAllActions() {
        actions.forEach(Action::perform);
        actions.clear();
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
