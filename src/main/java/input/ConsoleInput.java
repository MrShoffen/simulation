package input;

import game.controller.SimulationController;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleInput {
    public final static String ASK_FOR_WIDTH = "Введите ширину поля (число больше 3): ";
    public final static String ASK_FOR_HEIGHT = "Введите высоту поля (число больше 3): ";
    public final static Pattern PATTERN_FOR_WIDTH_HEIGHT_INPUT = Pattern.compile("[4-9][0-9]*|\\d{2,}"); //число от 4 и больше.

    public final static String ASK_FOR_UI = "Где выводить симуляцию?\n1: Консоль | 2: Swing UI \nВаш выбор:";
    public final static Pattern PATTERN_FOR_RENDERER_MENU_CHOICE = Pattern.compile("[12]"); //1||2

    public final static String ERROR_INPUT_TRY_AGAIN = "Неправильный ввод! Попробуйте еще раз.";

    public static SimulationController.UI chooseUi() {
        int result = readIntMatchingPattern(PATTERN_FOR_RENDERER_MENU_CHOICE);
        if (result == 1) {
            return SimulationController.UI.CONSOLE;
        } else {
            return SimulationController.UI.SWING;
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
