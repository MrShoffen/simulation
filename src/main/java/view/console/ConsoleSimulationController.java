package view.console;

import game.Simulation;
import view.SimulationController;

public class ConsoleSimulationController extends SimulationController {
    public final static String ASK_FOR_RUNNING_MENU_CHOICE = "1: Пауза | 2: Продолжить | 3: Выход";
//    Simulation simulation;



    public ConsoleSimulationController(Simulation simulation) {
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
