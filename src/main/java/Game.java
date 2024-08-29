import game.Simulation;
import view.IDEAConsoleMapRenderer;
import view.MapRenderer;
import view.swing.SwingMapRenderer;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Game {

    public final static String ASK_FOR_WIDTH = "Введите ширину поля (число больше 3): ";
    public final static String ASK_FOR_HEIGHT = "Введите высоту поля (число больше 3): ";
    public final static Pattern PATTERN_FOR_WIDTH_HEIGHT_INPUT = Pattern.compile("[4-9][0-9]*|\\d{2,}"); //число от 4 и больше.


    public final static String ASK_FOR_RENDERER_CHOICE = "Где выводить симуляцию?\n1: Консоль | 2: JavaFX \nВаш выбор:";
    public final static Pattern PATTERN_FOR_RENDERER_MENU_CHOICE = Pattern.compile("[12]"); //1||2

    public final static String ASK_FOR_STARTING_SIMULATION = "Симуляция создана\nЛегенда: \n" +
            "Поросёнок - травоядное. Цифра справа - текущее здоровье\nТигр - хищник. Цифра справа - здоровье, цифра слева - сила атаки\n\n" +
            "Нажмите Enter для старта симуляции.";
    public final static String ASK_FOR_RUNNING_MENU_CHOICE = "1: Пауза | 2: Продолжить | 3: Выход";
    public final static Pattern PATTERN_FOR_RUNNING_MENU_CHOICE = Pattern.compile("[123]"); // 1||2||3

    public final static String ERROR_INPUT_TRY_AGAIN = "Неправильный ввод! Попробуйте еще раз.";


    static MapRenderer chooseMapRenderer() {
        int result = readIntMatchingPattern(PATTERN_FOR_RENDERER_MENU_CHOICE);
        if (result == 1) {
            return new IDEAConsoleMapRenderer();
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

    public static void runSimulationInAnotherTrhead(Simulation sim) {
        GameThread game = new GameThread(sim);
        Thread gameThread = new Thread(game);

        gameThread.start();

        int choiceInRunningMenu;
        final int PAUSE = 1;
        final int RESUME = 2;
        final int EXIT  = 3;
        do {
            choiceInRunningMenu = readIntMatchingPattern(PATTERN_FOR_RUNNING_MENU_CHOICE);
            if(choiceInRunningMenu == PAUSE){
                game.pause();
            } else if(choiceInRunningMenu == RESUME){
                game.resume();
            }
        } while ( choiceInRunningMenu != EXIT);
        game.stop();

    }

}
