import view.IDEAConsoleMapRendere;
import view.MapRenderer;
import world.Map;

public class Test
{
    public static void main(String[] args) {


        Map test = new Map(5,8);
        MapRenderer renderer = new IDEAConsoleMapRendere(test);

//        System.out.println(test);

      //  test.updateMap();

//        System.out.println(test);

    }

}
