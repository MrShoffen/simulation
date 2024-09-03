package game.controller;

import game.Simulation;
import view.ConsoleMapRenderer;
import view.MapRenderer;
import view.SwingMapRenderer;
import world.map.GridMap;

public abstract class SimulationController implements Runnable {
    protected Simulation simulation;

    protected boolean gameRunning;
    protected boolean simulationIsAutoRunning;

    protected SimulationController(GridMap map, MapRenderer renderer) {
        simulation = new Simulation(map, renderer);
    }

    public abstract void startSimulation();

    public void pauseSimulation() {
        simulationIsAutoRunning = false;
    }

    public void resumeSimulation() {
        simulationIsAutoRunning = true;
    }

    public static SimulationController createFromUI(UI ui, GridMap map) {
        return switch (ui) {
            case SWING -> new SwingSimulationController(map, new SwingMapRenderer());
            case CONSOLE -> new ConsoleSimulationController(map, new ConsoleMapRenderer());
        };
    }

    public enum UI {
        CONSOLE,
        SWING
    }
}
