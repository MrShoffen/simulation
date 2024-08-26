import game.Simulation;
import view.IDEAConsoleMapRenderer;
import view.MapRenderer;
import world.Map;

public class StartingMenu
{


    public static void main(String[] args) throws InterruptedException {

//
//        System.out.println(Game.ASK_FOR_WIDTH);
//        int width = Game.readIntMatchingPattern(Game.PATTERN_FOR_WIDTH_HEIGHT_INPUT);
//
//        System.out.println(Game.ASK_FOR_HEIGHT);
//        int height = Game.readIntMatchingPattern(Game.PATTERN_FOR_WIDTH_HEIGHT_INPUT);
//
//
//        Map map = new Map(height,width);
//
//        System.out.println(Game.ASK_FOR_RENDERER_CHOICE);
//        MapRenderer renderer = Game.chooseMapRenderer();
//        renderer.setMap(map);

        Map map = new Map(15 ,15);
        MapRenderer renderer = new IDEAConsoleMapRenderer();
        renderer.setMap(map);
        Simulation simulation  = new Simulation(map,renderer);

        Game.runSimulationInAnotherTrhead(simulation);



    }







}
