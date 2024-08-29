package view.controllers;

import game.Simulation;

public class ConsoleController extends SimulationController {
    public final static String ASK_FOR_RUNNING_MENU_CHOICE = "1: Пауза | 2: Продолжить | 3: Выход";
//    Simulation simulation;



    public ConsoleController(Simulation simulation) {
        super(simulation);
    }

    @Override
    public void run() {
        gameRunning = true;
        resume();
        while(gameRunning){
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(simulation.isAutoRunning()){
                try {
                    simulation.nextTurn();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(ASK_FOR_RUNNING_MENU_CHOICE + '\n');
            }
        }
    }

}
