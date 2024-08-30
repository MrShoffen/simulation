package view.console;

import input.ConsoleInput;
import game.Simulation;
import view.SimulationController;

import java.util.regex.Pattern;

public class ConsoleSimulationController extends SimulationController {
    public final static String ASK_FOR_RUNNING_MENU_CHOICE = "1: Пауза | 2: Продолжить | 3: Выход";
    public final static Pattern PATTERN_FOR_RUNNING_MENU_CHOICE = Pattern.compile("[123]"); // 1||2||3


    public ConsoleSimulationController(Simulation simulation) {
        super(simulation);
    }

    @Override
    public void run() {
        gameRunning = true;
        resume();
        while (gameRunning) {
            Simulation.delay(750);
            if (simulation.isAutoRunning()) {
                simulation.nextTurn();
                System.out.println(ASK_FOR_RUNNING_MENU_CHOICE + '\n');
            }
        }
    }


    public static void runConsoleSimulation(Simulation sim) {
        SimulationController game = new ConsoleSimulationController(sim);
        Thread gameThread = new Thread(game);

        gameThread.start();

        int choiceInRunningMenu;
        final int PAUSE = 1;
        final int RESUME = 2;
        final int EXIT  = 3;
        do {
            choiceInRunningMenu = ConsoleInput.readIntMatchingPattern(PATTERN_FOR_RUNNING_MENU_CHOICE);
            if(choiceInRunningMenu == PAUSE){
                game.pause();
            } else if(choiceInRunningMenu == RESUME){
                game.resume();
            }
        } while ( choiceInRunningMenu != EXIT);
        game.stop();
    }

}
