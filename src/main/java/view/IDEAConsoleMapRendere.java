package view;

import world.Cell;
import world.Map;
import world.entities.Entity;
import world.entities.creatures.Herbivore;
import world.entities.creatures.Predator;
import world.entities.objects.Grass;
import world.entities.objects.Rock;
import world.entities.objects.Tree;

public class IDEAConsoleMapRendere implements MapRenderer {
    private final Map map;
    private final int height;
    private final int width;

    private static final String PREDATOR_EMOJI = "\uD83D\uDC2F";
    private static final String HERBIVORE_EMOJI = "\uD83D\uDC16";
    private static final String GRASS_EMOJI = "\uD83C\uDF31";
    private static final String ROCK_EMOJI = "\uD83E\uDEA8";
    private static final String TREE_EMOJI = "\uD83C\uDF33";
    private static final String EMPTY_CELL_EMOJI = "\uD83D\uDFEB";

    private static final String ATTACK_COLOR = "\u001B[31m";
    private static final String HEALTH_COLOR = "\u001B[32m";
    private static final String RESET_COLOR = "\u001B[0m";

    public IDEAConsoleMapRendere(Map map) {
        this.map = map;
        height = map.getHeight();
        width = map.getWidth();
    }

    @Override
    public void render() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < height; i++) {
            result.append(lineWithCells(i));
        }

        System.out.println(result);
    }


    private StringBuilder lineWithCells(int lineNumber) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < width; i++) {
            result.append(cell(i, lineNumber));
        }
        result.append("\n");
        return result;
    }

    private StringBuilder cell(int x, int y) {
        StringBuilder result = new StringBuilder();
        Cell cell = map.getCellAt(x, y);
        if (!cell.hasEntity()) {
            return result.append(" " + EMPTY_CELL_EMOJI + " ");
        }
        return result.append(entity(cell.getEntity()));
    }

    private String entity(Entity entity) throws IllegalStateException {
        return
                switch (entity) {
                    case Predator _ -> ATTACK_COLOR + "1" + HEALTH_COLOR + PREDATOR_EMOJI + "2" + RESET_COLOR;
                    case Herbivore _ -> HEALTH_COLOR +" "+ HERBIVORE_EMOJI + "2" + RESET_COLOR;
                    case Grass _ ->  " " + GRASS_EMOJI + " ";
                    case Rock _ -> " " + ROCK_EMOJI + " ";
                    case Tree _ -> " " + TREE_EMOJI + " ";
                    case null, default -> throw new IllegalStateException();
                };
    }

    public static void main(String[] args) {

        Map map = new Map(10, 10);

        map.setEntity(new Predator(), 0, 0);

        map.setEntity(new Herbivore(), 2, 2);
        map.setEntity(new Grass(),1,0);
        map.setEntity(new Tree(),0,1);
        map.setEntity(new Rock(),2,4);

        System.out.println( map.contains(new Cell (2,2)));


        MapRenderer renderer = new IDEAConsoleMapRendere(map);
        renderer.render();
        var a= map.allEntities();
        System.out.println(a);
//        renderer.render();
//        for (int i = 1; i < 4; i++) {
////            System.out.print("\033[H\033[J");
//
//            System.out.println("\n\n\n\n\n\n\n");
////            System.out.flush();
//            renderer.render();
//            map.moveEntity(i - 1, i - 1, i, i);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }


    }
}
//    }


//    public static void main(String[] args) {
//        // Создаем пул из одного потока для обработки ввода
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        // Запускаем поток для ввода
//        executor.submit(() -> {
//            Scanner scanner = new Scanner(System.in);
//            while (true) {
//                System.out.print("Введите информацию: ");
//                String input = scanner.nextLine();
//                System.out.println("Вы ввели: " + input);
//            }
//        });
//
//        // Основной поток
//        while (true) {
//            // Основная логика программы
//            System.out.println("Основной поток работает...");
//            try {
//                Thread.sleep(1000); // Имитация работы
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt(); // Восстановление прерывания, если поток прерван
//                break;
//            }
//        }
//
//        // Завершение работы executor
//        executor.shutdown();
//    }
