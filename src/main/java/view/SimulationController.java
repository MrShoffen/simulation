package view;

import game.Simulation;
import view.console.ConsoleSimulationController;
import view.swing.SwingMapRenderer;
import view.swing.SwingSimulationController;

public abstract class SimulationController implements Runnable {

    protected Simulation simulation;

    protected boolean gameRunning;
    protected boolean simulationIsAutoRunning;


    protected SimulationController(Simulation sim) {
        simulation = sim;
    }

    public void pause() {
//        if (simulationIsAutoRunning){
            simulationIsAutoRunning = false;
//        }
    }

    public void resume() {
//        if (!simulationIsAutoRunning){
            simulationIsAutoRunning = true;
//        }
    }

    public void stop() {
        pause();
        gameRunning = false;
    }

    public static void runSimulation(Simulation sim) {
        if (sim.getRenderer() instanceof SwingMapRenderer) {
            SimulationController swingController = new SwingSimulationController(sim);
            new Thread(swingController).start();
        } else {
            ConsoleSimulationController.runConsoleSimulation(sim);
        }
    }

    public static void delay(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
