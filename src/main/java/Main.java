import game.controller.SimulationController;
import input.ConsoleInput;
import world.map.GridMap;

public class Main {
    public static void main(String[] args) {
        System.out.println(ConsoleInput.ASK_FOR_WIDTH);
        int width = ConsoleInput.readIntMatchingPattern(ConsoleInput.PATTERN_FOR_WIDTH_HEIGHT_INPUT);
        System.out.println(ConsoleInput.ASK_FOR_HEIGHT);
        int height = ConsoleInput.readIntMatchingPattern(ConsoleInput.PATTERN_FOR_WIDTH_HEIGHT_INPUT);
        GridMap map = new GridMap(height, width);

        System.out.println(ConsoleInput.ASK_FOR_UI);
        SimulationController.UI ui = ConsoleInput.chooseUi();

        SimulationController controller = SimulationController.createFromUI(ui, map);

        controller.startSimulation();
    }


}
