import game.Simulation;
import view.MapRenderer;
import world.GridMap;

public class StartingMenu
{


    public static void main(String[] args) throws InterruptedException {


        System.out.println(Game.ASK_FOR_WIDTH);
        int width = Game.readIntMatchingPattern(Game.PATTERN_FOR_WIDTH_HEIGHT_INPUT);
        System.out.println(Game.ASK_FOR_HEIGHT);
        int height = Game.readIntMatchingPattern(Game.PATTERN_FOR_WIDTH_HEIGHT_INPUT);
        GridMap map = new GridMap(height,width);

        System.out.println(Game.ASK_FOR_RENDERER_CHOICE);
        MapRenderer renderer = Game.chooseMapRenderer();
//        GridMap map = new GridMap(15,15);
//        MapRenderer renderer = new SwingMapRenderer();
//        MapRenderer renderer = new ConsoleMapRenderer();
        renderer.setMap(map);

        Simulation simulation  = new Simulation(map,renderer);


        Game.runSimulationInAnotherTrhead(simulation);



    }







}
