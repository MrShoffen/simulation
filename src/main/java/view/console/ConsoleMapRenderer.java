package view.console;

import view.MapRenderer;
import world.Cell;
import world.GridMap;
import world.entities.Entity;
import world.entities.creatures.Herbivore;
import world.entities.creatures.Predator;
import world.entities.objects.Grass;
import world.entities.objects.Rock;
import world.entities.objects.Tree;

public final class ConsoleMapRenderer implements MapRenderer {

    private static final String PREDATOR_EMOJI = "\uD83D\uDC2F";
    private static final String HERBIVORE_EMOJI = "\uD83D\uDC16";
    private static final String GRASS_EMOJI = "\uD83C\uDF31";
    private static final String ROCK_EMOJI = "\uD83E\uDEA8";
    private static final String TREE_EMOJI = "\uD83C\uDF33";
    private static final String EMPTY_CELL_EMOJI = "\uD83D\uDFEB";

    private static final String ATTACK_COLOR = "\u001B[31m";
    private static final String HEALTH_COLOR = "\u001B[32m";
    private static final String RESET_COLOR = "\u001B[0m";


    @Override
    public void render(GridMap map) {
        StringBuilder result = new StringBuilder();
        for (int lineNumber = 0; lineNumber < map.getHeight(); lineNumber++) {
            result.append(lineWithCells(map, lineNumber));
        }

        System.out.print(result);
    }


    private StringBuilder lineWithCells(GridMap map, int lineNumber) {
        StringBuilder result = new StringBuilder();
        for (int rowNumber = 0; rowNumber < map.getWidth(); rowNumber++) {
            Cell cell = map.getCellAt(rowNumber,lineNumber);
            result.append(cellString(cell));
        }
        result.append("\n");
        return result;
    }

    private StringBuilder cellString(Cell cell) {
        StringBuilder result = new StringBuilder();
        if (!cell.hasEntity()) {
            return result.append(" " + EMPTY_CELL_EMOJI + " ");
        }
        return result.append(entity(cell.getEntity()));
    }

    private String entity(Entity entity) throws IllegalStateException {
        return
                switch (entity) {
                    case Predator predator ->
                            ATTACK_COLOR + predator.getAttack() + HEALTH_COLOR + PREDATOR_EMOJI + predator.currentHealth() + RESET_COLOR;
                    case Herbivore herbivore ->
                            HEALTH_COLOR + " " + HERBIVORE_EMOJI + herbivore.currentHealth() + RESET_COLOR;
                    case Grass _ -> " " + GRASS_EMOJI + " ";
                    case Rock _ -> " " + ROCK_EMOJI + " ";
                    case Tree _ -> " " + TREE_EMOJI + " ";
                    case null, default -> throw new IllegalStateException();
                };
    }
}