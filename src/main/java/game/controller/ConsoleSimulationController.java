package game.controller;

import input.ConsoleInput;
import view.ConsoleMapRenderer;
import world.map.GridMap;

import java.util.regex.Pattern;

public class ConsoleSimulationController extends SimulationController {
    public final static String ASK_FOR_RUNNING_MENU_CHOICE = "1: Пауза | 2: Продолжить | 3: Выход";
    public final static Pattern PATTERN_FOR_RUNNING_MENU_CHOICE = Pattern.compile("[123]"); // 1||2||3

    ConsoleSimulationController(GridMap map, ConsoleMapRenderer renderer) {
        super(map, renderer);
    }

    @Override
    public void run() {
        while (gameRunning) {
            if (simulationIsAutoRunning) {
                simulation.nextTurn();
                System.out.println(ASK_FOR_RUNNING_MENU_CHOICE + '\n');
            }
        }
    }

    @Override
    public void startSimulation() {
        gameRunning = true;
        simulationIsAutoRunning = true;
        new Thread(this).start();
        readConsoleInputForRunningSimulation();
    }

    public void stopSimulation() {
        pauseSimulation();
        gameRunning = false;
    }

    private void readConsoleInputForRunningSimulation() {
        int choiceInRunningMenu;
        final int PAUSE = 1;
        final int RESUME = 2;
        final int EXIT = 3;
        do {
            choiceInRunningMenu = ConsoleInput.readIntMatchingPattern(PATTERN_FOR_RUNNING_MENU_CHOICE);
            if (choiceInRunningMenu == PAUSE) {
                this.pauseSimulation();
            } else if (choiceInRunningMenu == RESUME) {
                this.resumeSimulation();
            }
        } while (choiceInRunningMenu != EXIT);
        this.stopSimulation();
    }
}
