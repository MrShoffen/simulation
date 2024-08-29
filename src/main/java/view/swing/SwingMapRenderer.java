package view.swing;

import game.Simulation;
import view.IDEAConsoleMapRenderer;
import view.MapRenderer;
import world.GridMap;

import javax.swing.*;
import java.awt.*;

public class SwingMapRenderer extends JFrame implements MapRenderer {

    SwingMap map;

    GridMap gridMap;


    public SwingMapRenderer() {
//        this.map = map;
        setTitle("Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new GridLayout(6, 6)); // 4x4 сетка
//        frame.setLayout();
        setResizable(false);


    }

    @Override
    public void setMap(GridMap map) {
        gridMap = map;

        this.map = new SwingMap(map);
        initializeWindow();
    }

    private void initializeWindow() {
        // Создаем кнопку для обновления содержимого
        JButton startPauseButton = new JButton("Update Panel");
        JSlider slider = new JSlider();

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Для пространства между элементами
        controlPanel.add(startPauseButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Для пространства между элементами
        controlPanel.add(slider);
        // Добавляем ActionListener к startPauseButton


        getContentPane().add(map, BorderLayout.WEST);
        getContentPane().add(controlPanel, BorderLayout.EAST);
        pack();

    }

    void launch() {
        setVisible(true);
    }

    @Override
    public void render() {
        map.render();
    }

    public static void main(String[] args) throws InterruptedException {
        GridMap map = new GridMap(20, 30);
//        Simulation sim = new Simulation(map, new IDEAConsoleMapRenderer());


        SwingMapRenderer w = new SwingMapRenderer();
        w.setMap(map);
        Simulation s = new Simulation(map, w);
        w.launch();
        while (true) {
            s.nextTurn();
        }

    }
}
