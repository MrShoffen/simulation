package view.swing;

import view.MapRenderer;
import world.Cell;
import world.GridMap;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class SwingMapRenderer extends JPanel implements MapRenderer {
    Map<Cell, SwingCell> swingCell = new HashMap<>();
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
        for (Cell cell : swingCell.keySet()) {
            swingCell.get(cell).refresh(cell);
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
                Cell c = map.getCellAt(x, y);
                SwingCell swCell = new SwingCell(c);
                add(swCell);
                swingCell.put(c, swCell);
            }
        }
    }
}
