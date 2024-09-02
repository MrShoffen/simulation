package view;

import world.map.Cell;
import world.map.GridMap;
import world.entities.Entity;
import world.entities.creatures.Creature;
import world.entities.creatures.Herbivore;
import world.entities.creatures.Predator;
import world.entities.environment.Grass;
import world.entities.environment.Rock;
import world.entities.environment.Tree;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class SwingMapRenderer extends JPanel implements MapRenderer {
    private static final Dimension TEXT_LABEL_DIMENSION = new Dimension(42, 10);
    private static final Dimension IMAGE_DIMENSION = new Dimension(48, 26);
    private static final Dimension CELL_DIMENSION = new Dimension(48, 48);

    private static final Color EMPTY_CELL_COLOR = new Color(224, 181, 128, 163);
    private static final Color ATTACK_TEXT_COLOR = new Color(124, 13, 13);
    private static final Color HEALTH_TEXT_COLOR = new Color(7, 119, 8);
    private static final Color MOVING_CREATURE_COLOR = new Color(143, 162, 126, 255);

    private static final ImageIcon PREDATOR_IMAGE = new ImageIcon("src" + File.separator + "main" + File.separator + "resources" + File.separator + "predator.png");
    private static final ImageIcon ROCK_IMAGE = new ImageIcon("src" + File.separator + "main" + File.separator + "resources" + File.separator + "rock.png");
    private static final ImageIcon GRASS_IMAGE = new ImageIcon("src" + File.separator + "main" + File.separator + "resources" + File.separator + "grass.png");
    private static final ImageIcon TREE_IMAGE = new ImageIcon("src" + File.separator + "main" + File.separator + "resources" + File.separator + "tree.png");
    private static final ImageIcon HERBIVORE_IMAGE = new ImageIcon("src" + File.separator + "main" + File.separator + "resources" + File.separator + "herbivore.png");


    Map<Cell, JPanel> swingCells = new HashMap<>();
    boolean firstRender;

    public SwingMapRenderer() {
        setLayout(new GridLayout());
        firstRender = true;
    }

    @Override
    public void render(GridMap map) {
        if (firstRender) {
            initRender(map);
            firstRender = false;
        }
        for (Cell cell : swingCells.keySet()) {
            refreshSwingCell(swingCells.get(cell),cell,map);
        }
        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
        });
    }

    public void initRender(GridMap map) {
        ((GridLayout) getLayout()).setColumns(map.getWidth());
        ((GridLayout) getLayout()).setRows(map.getHeight());

        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                Cell c = new Cell(x, y);
                JPanel swingCell = generateJPanelWithCell(map, c);
                add(swingCell);
                swingCells.put(c, swingCell);
            }
        }
    }

    private void refreshSwingCell(JPanel swingCell, Cell cell, GridMap map){
        swingCell.removeAll();
        if (map.getEntity(cell).isEmpty()) {
            swingCell.setBackground(EMPTY_CELL_COLOR);
        } else {
            addEntityToCell(swingCell,map.getEntity(cell).get());
        }
    }


    private JPanel generateJPanelWithCell(GridMap map, Cell cell) {
        JPanel JPanelWithCell = new JPanel();
        JPanelWithCell.setLayout(new BoxLayout(JPanelWithCell, BoxLayout.Y_AXIS));
        JPanelWithCell.setBorder(new LineBorder(Color.BLACK));
        JPanelWithCell.setBackground(EMPTY_CELL_COLOR);
        JPanelWithCell.setPreferredSize(CELL_DIMENSION);

        if (map.getEntity(cell).isPresent()) {
            addEntityToCell(JPanelWithCell, map.getEntity(cell).get());
        }

        return JPanelWithCell;
    }

    private void addEntityToCell(JPanel cell, Entity entity) {
        if (entity instanceof Creature) {
            addCreatureToCell(cell, (Creature) entity);
        } else {
            addNonCreatureToCell(cell, entity);
        }
    }

    private void addNonCreatureToCell(JPanel swingCell, Entity entity) {
        ImageIcon print =
                switch (entity) {
                    case Grass _ -> GRASS_IMAGE;
                    case Rock _ -> ROCK_IMAGE;
                    case Tree _ -> TREE_IMAGE;
                    case null, default -> throw new IllegalStateException();
                };
        swingCell.add(imageLabel(print,CELL_DIMENSION));
    }

    private void addCreatureToCell(JPanel swingCell, Creature creature) {
        if (creature.canMove()) {
            swingCell.setBackground(MOVING_CREATURE_COLOR);
        } else {
            swingCell.setBackground(EMPTY_CELL_COLOR);
        }

        ImageIcon creaturePicture;
        String health = creature.currentHealth() + "/" + creature.maxHealth();
        String attack = "";
        switch (creature) {
            case Predator predator -> {
                creaturePicture = PREDATOR_IMAGE;
                attack = String.valueOf(predator.getAttack());
            }
            case Herbivore _ -> creaturePicture = HERBIVORE_IMAGE;
            case null, default -> throw new IllegalStateException();
        }

        swingCell.add(healthLabel(health));
        swingCell.add(imageLabel(creaturePicture,IMAGE_DIMENSION));
        swingCell.add(attackLabel(attack));
    }

    private JLabel attackLabel(String attack) {
        JLabel attackLabel = new JLabel(attack);
        attackLabel.setPreferredSize(TEXT_LABEL_DIMENSION);
        attackLabel.setForeground(ATTACK_TEXT_COLOR);
        return attackLabel;
    }

    private JLabel healthLabel(String health) {
        JLabel healthLabel = new JLabel(health);
        healthLabel.setMaximumSize(TEXT_LABEL_DIMENSION);
        healthLabel.setPreferredSize(TEXT_LABEL_DIMENSION);
        healthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        healthLabel.setForeground(HEALTH_TEXT_COLOR);
        return healthLabel;
    }

    private JLabel imageLabel(ImageIcon image, Dimension dim) {
        JLabel imageLabel = new JLabel(image);
        imageLabel.setMinimumSize(dim);
        imageLabel.setMaximumSize(dim);
        return imageLabel;
    }
}
