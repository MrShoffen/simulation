package view;

import world.map.Cell;
import world.map.GridMap;
import world.entities.Entity;
import world.entities.creatures.Herbivore;
import world.entities.creatures.Predator;
import world.entities.environment.Grass;
import world.entities.environment.Rock;
import world.entities.environment.Tree;

public final class ConsoleMapRenderer implements MapRenderer {

    private static final String PREDATOR_EMOJI = "\uD83D\uDC2F";
    private static final String HERBIVORE_EMOJI = "\uD83D\uDC16";
    private static final String GRASS_EMOJI = "\uD83C\uDF31";
    private static final String ROCK_EMOJI = "\uD83E\uDEA8";
    private static final String TREE_EMOJI = "\uD83C\uDF33";
    private static final String EMPTY_CELL_EMOJI = "\uD83D\uDFEB";

    private static final String ATTACK_COLOR = ANSIIColor.RED.getCode();
    private static final String HEALTH_COLOR = ANSIIColor.GREEN.getCode();
    private static final String RESET_COLOR = ANSIIColor.DEFAULT.getCode();

    @Override
    public void render(GridMap map) {
        StringBuilder result = new StringBuilder();

        for (int line = 0; line < map.getHeight(); line++) {
            for (int row = 0; row < map.getWidth(); row++) {
                result.append(stringFromCell(new Cell(row, line), map));
            }
            result.append('\n');
        }

        System.out.print(result + "\n");
    }

    private String stringFromCell(Cell cell, GridMap map) {
        if (map.getEntity(cell).isPresent()) {
            return stringFromEntity(map.getEntity(cell).get());
        } else {
            return stringFromEmptyCell();
        }
    }

    private String stringFromEntity(Entity entity) {
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

    private String stringFromEmptyCell() {
        return " " + EMPTY_CELL_EMOJI + " ";
    }

    private enum ANSIIColor {
        DEFAULT("\u001B[0m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m");

        private final String code;

        ANSIIColor(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}