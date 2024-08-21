import game.Simulation;
import view.FXRenderer;
import view.IDEAConsoleMapRenderer;
import view.MapRenderer;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Game {

    public final static String ASK_FOR_WIDTH= "Введите ширину поля (число больше 3): ";
    public final static String ASK_FOR_HEIGHT = "Введите высоту поля (число больше 3): ";
    public final static Pattern PATTERN_FOR_WIDTH_HEIGHT_INPUT = Pattern.compile("[4-9][0-9]*|\\d{2,}");


    public final static String ASK_FOR_RENDERER_CHOICE = "Где выводить симуляцию?\n1: Консоль\n2: JavaFX\nВаш выбор:";
    public final static Pattern PATTERN_FOR_RENDERER_CHOICE = Pattern.compile("[12]");

    public final static String ERROR_INPUT_TRY_AGAIN = "Неправильный ввод! Попробуйте еще раз.";


    static MapRenderer chooseMapRenderer() {
        int result = readIntMatchingPattern(PATTERN_FOR_RENDERER_CHOICE);
        if (result == 1){
            return new IDEAConsoleMapRenderer();
        } else{
            return new FXRenderer();
        }

    }

    public static int readIntMatchingPattern(Pattern pattern){
        Scanner scan = new Scanner(System.in);
        while (true) {
            String input = scan.next();
            if (pattern.matcher(input).matches()){
                return Integer.parseInt(input);
            } else{
                System.out.println(ERROR_INPUT_TRY_AGAIN);
            }
        }
    }

    public static void runSimulationInAnotherTrhead(Simulation sim){
        Runnable run = sim::start;
        run.run();
    }

}
