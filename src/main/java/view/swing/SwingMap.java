package view.swing;

import game.Simulation;
import view.IDEAConsoleMapRenderer;
import world.GridMap;

import javax.swing.*;
import java.awt.*;


public class SwingMap extends JPanel {
        int width;
        int height;

        GridMap map;
    SwingMap(GridMap map){
        width = map.getWidth();
        height = map.getHeight();
        this.map = map;
        setLayout(new GridLayout(height,width));
        setDoubleBuffered(true);
        render();
    }

    void render(){
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
