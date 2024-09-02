package game.controller;

import game.Simulation;
import world.map.GridMap;

public abstract class SimulationController implements Runnable {
    protected Simulation simulation;

    protected boolean gameRunning;
    protected boolean simulationIsAutoRunning;

    protected SimulationController(){}

    public abstract void startSimulation();

    public void pauseSimulation() {
            simulationIsAutoRunning = false;
    }

    public void resumeSimulation() {
            simulationIsAutoRunning = true;
    }

    public static SimulationController createFromUI(UI ui, GridMap map){
        return switch (ui){
            case SWING -> new SwingSimulationController(map);
            case CONSOLE -> new ConsoleSimulationController(map);
        };
    }

    public static void delay(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public enum UI {
        CONSOLE,
        SWING
    }
}
