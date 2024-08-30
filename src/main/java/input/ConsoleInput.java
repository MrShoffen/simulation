package input;

import game.Simulation;
import view.console.ConsoleSimulationController;
import view.SimulationController;
import view.console.ConsoleMapRenderer;
import view.MapRenderer;
import view.swing.SwingSimulationController;
import view.swing.SwingMapRenderer;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleInput {
    public final static String ASK_FOR_WIDTH = "Введите ширину поля (число больше 3): ";
    public final static String ASK_FOR_HEIGHT = "Введите высоту поля (число больше 3): ";
    public final static Pattern PATTERN_FOR_WIDTH_HEIGHT_INPUT = Pattern.compile("[4-9][0-9]*|\\d{2,}"); //число от 4 и больше.

    public final static String ASK_FOR_RENDERER_CHOICE = "Где выводить симуляцию?\n1: Консоль | 2: Swing UI \nВаш выбор:";
    public final static Pattern PATTERN_FOR_RENDERER_MENU_CHOICE = Pattern.compile("[12]"); //1||2

    public final static String ERROR_INPUT_TRY_AGAIN = "Неправильный ввод! Попробуйте еще раз.";

    public static MapRenderer chooseMapRenderer() {
        int result = readIntMatchingPattern(PATTERN_FOR_RENDERER_MENU_CHOICE);
        if (result == 1) {
            return new ConsoleMapRenderer();
        } else {
            return new SwingMapRenderer();
        }
    }

    public static int readIntMatchingPattern(Pattern pattern) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            String input = scan.next();
            if (pattern.matcher(input).matches()) {
                return Integer.parseInt(input);
            } else {
                System.out.println(ERROR_INPUT_TRY_AGAIN);
            }
        }
    }




}
