package view.swing;

import world.Cell;
import world.entities.Entity;
import world.entities.creatures.Creature;
import world.entities.creatures.Herbivore;
import world.entities.creatures.Predator;
import world.entities.objects.Grass;
import world.entities.objects.Rock;
import world.entities.objects.Tree;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SwingCell extends JPanel {
    private static final Dimension TEXT_LABEL_DIMENSION = new Dimension(42, 10);
    private static final Dimension IMAGE_DIMENSION = new Dimension(48, 26);
    private static final Dimension CELL_DIMENSION = new Dimension(48, 48);

    private static final Color EMPTY_CELL_COLOR = new Color(224, 181, 128, 163);
    private static final Color ATTACK_TEXT_COLOR = new Color(124, 13, 13);
    private static final Color HEALTH_TEXT_COLOR = new Color(7, 119, 8);
    private static final Color MOVING_CREATURE_COLOR = new Color(143, 162, 126, 255);


    public SwingCell(Cell cell) {
        initEmptyCell();
        if (!cell.hasEntity())
            return;

        if (cell.getEntity() instanceof Creature) {
            initCreatureCell((Creature) cell.getEntity());
        } else {
            initNonCreatureCell(cell.getEntity());
        }


    }

    private void initNonCreatureCell(Entity entity) {
        ImageIcon print =
                switch (entity) {
                    case Grass _ -> Images.GRASS;
                    case Rock _ -> Images.ROCK;
                    case Tree _ -> Images.TREE;
                    case null, default -> throw new IllegalStateException();
                };
        setImage(print, CELL_DIMENSION);
    }

    private void initCreatureCell(Creature creature) {
        ImageIcon print;
        String health = creature.currentHealth() + "/" + creature.maxHealth();
        if (creature.canMove()) {
            setBackground(MOVING_CREATURE_COLOR);
        }
        String attack = "";
        switch (creature) {
            case Predator predator -> {
                print = Images.PREDATOR;
                attack = String.valueOf(predator.getAttack());
            }
            case Herbivore _ -> {
                print = Images.HERBIVORE;
            }
            case null, default -> throw new IllegalStateException();
        }

        setHealth(health);

        setImage(print, IMAGE_DIMENSION);

        setAttack(attack);

    }

    private void setAttack(String attack) {
        JLabel attackLabel = new JLabel(attack);
        attackLabel.setMaximumSize(TEXT_LABEL_DIMENSION);
        attackLabel.setPreferredSize(TEXT_LABEL_DIMENSION);
        attackLabel.setForeground(ATTACK_TEXT_COLOR);
        add(attackLabel);
    }

    private void setHealth(String health) {
        JLabel healthLabel = new JLabel(health);
        healthLabel.setMaximumSize(TEXT_LABEL_DIMENSION);
        healthLabel.setPreferredSize(TEXT_LABEL_DIMENSION);
        healthLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        healthLabel.setForeground(HEALTH_TEXT_COLOR);

        add(healthLabel);
    }

    private void setImage(ImageIcon image, Dimension dim) {
        JLabel entityImage = new JLabel(image);
        entityImage.setMinimumSize(dim);
        entityImage.setMaximumSize(dim);
        add(entityImage);
    }

    private void initEmptyCell() {
        BoxLayout mgr = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(mgr);

        setBorder(new LineBorder(Color.BLACK));
        setBackground(EMPTY_CELL_COLOR);

        setPreferredSize(CELL_DIMENSION);
    }
}
