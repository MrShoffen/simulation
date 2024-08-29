package view;

import game.Simulation;

public abstract class SimulationController implements Runnable {
    protected Simulation simulation;

    protected boolean gameRunning;


    protected SimulationController(Simulation sim){
        simulation = sim;
    }

    public void pause(){
        if(simulation.isAutoRunning()) {
            simulation.pause();
        }
    }
    public void resume(){
        if(!simulation.isAutoRunning()) {
            simulation.resume();
        }
    }

    public void stop(){
        pause();
        gameRunning = false;
    }
}
