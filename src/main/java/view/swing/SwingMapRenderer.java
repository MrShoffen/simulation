package view.swing;

import view.MapRenderer;
import world.GridMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class SwingMapRenderer extends JPanel implements MapRenderer {

    public SwingMapRenderer() {
        setLayout(new GridLayout());
        setDoubleBuffered(true);
    }

    @Override
    public void render(GridMap map) {
        ((GridLayout) getLayout()).setColumns(map.getWidth());
        ((GridLayout) getLayout()).setRows(map.getHeight());

        removeAll();
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                add(new SwingCell(map.getCellAt(x, y)));
            }
        }
        SwingUtilities.invokeLater(() -> {
            revalidate();
            repaint();
        });
    }
}
