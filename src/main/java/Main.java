import input.ConsoleInput;
import game.Simulation;
import view.MapRenderer;
import view.SimulationController;
import world.GridMap;

public class Main
{


    public static void main(String[] args)   {
        System.out.println(ConsoleInput.ASK_FOR_WIDTH);
        int width = ConsoleInput.readIntMatchingPattern(ConsoleInput.PATTERN_FOR_WIDTH_HEIGHT_INPUT);
        System.out.println(ConsoleInput.ASK_FOR_HEIGHT);
        int height = ConsoleInput.readIntMatchingPattern(ConsoleInput.PATTERN_FOR_WIDTH_HEIGHT_INPUT);
        GridMap map = new GridMap(height,width);

        System.out.println(ConsoleInput.ASK_FOR_RENDERER_CHOICE);
        MapRenderer renderer = ConsoleInput.chooseMapRenderer();

        Simulation simulation  = new Simulation(map,renderer);

        SimulationController.runSimulation(simulation);
    }







}
