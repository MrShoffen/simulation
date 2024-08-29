package view.swing;

import view.MapRenderer;
import world.GridMap;

import javax.swing.*;
import java.awt.*;


public class SwingMapRenderer extends JPanel implements MapRenderer {
        int width;
        int height;

        GridMap map;

    @Override
    public void setMap(GridMap map) {
        this.map = map;
        width = map.getWidth();
        height = map.getHeight();
        setLayout(new GridLayout(height,width));
        setDoubleBuffered(true);
        render();
    }
@Override
    public void render(){
        removeAll();
        for (int y = 0; y < map.getHeight(); y++){
            for(int x = 0; x < map.getWidth(); x++){
                add(new SwingCell(map.getCellAt(x,y)));
            }
        }
        SwingUtilities.invokeLater(() -> {
            // Обновления интерфейса здесь
            revalidate();
            repaint();
        });
    }

}
