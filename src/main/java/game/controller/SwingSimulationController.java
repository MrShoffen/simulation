package game.controller;


import view.MapRenderer;
import view.SwingMapRenderer;
import world.entities.creatures.Herbivore;
import world.entities.creatures.Predator;
import world.map.GridMap;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class SwingSimulationController extends SimulationController {
    public final static Image ICON = Toolkit.getDefaultToolkit().getImage("src" + File.separator + "main" + File.separator + "resources" + File.separator + "icon.png");

    JFrame mainWindow;
    JSlider speedSlider;
    private JLabel simulationCount;
    private JLabel predatorCount;
    private JLabel herbivoreCount;

    SwingSimulationController(GridMap map, SwingMapRenderer swingMapRenderer) {
        super(map, swingMapRenderer);

        initializeWindow(swingMapRenderer);
    }

    @Override
    public void run() {
        while (gameRunning) {
            if (simulationIsAutoRunning) {
                simulation.nextTurn();
            }
            updateSimulationStats();
        }
    }

    @Override
    public void startSimulation() {
        launchWindow();
        gameRunning = true;
        simulationIsAutoRunning = false;
        new Thread(this).start();
    }

    private void initializeWindow(MapRenderer renderer) {
        mainWindow = new JFrame("Simulation");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        mainWindow.setIconImage(ICON);

        JPanel controlPanel = createControlPanel();
        speedSlider.addChangeListener(_ -> renderer.setDelayTime(speedSlider.getValue()));

        mainWindow.getContentPane().add((SwingMapRenderer) renderer, BorderLayout.WEST);
        mainWindow.getContentPane().add(controlPanel, BorderLayout.EAST);
        mainWindow.pack();
    }

    private JPanel createControlPanel() {
        JButton startPauseButton = createStartPauseButton();
        JLabel sliderLabel = new JLabel("Скорость симуляции");
        speedSlider = createSpeedSlider();
        simulationCount = new JLabel("Шаг симуляции: " + simulation.getSimulationCount());
        predatorCount = new JLabel("Хищники: ");
        herbivoreCount = new JLabel("Травоядные: ");

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        controlPanel.add(startPauseButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        controlPanel.add(sliderLabel);
        controlPanel.add(speedSlider);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        controlPanel.add(simulationCount);
        controlPanel.add(predatorCount);
        controlPanel.add(herbivoreCount);
        return controlPanel;
    }

    private void updateSimulationStats() {
        simulationCount.setText("Шаг симуляции: " + simulation.getSimulationCount());
        predatorCount.setText("Хищники: " + simulation.getEntityCountByType(Predator.class));
        herbivoreCount.setText("Травоядные: " + simulation.getEntityCountByType(Herbivore.class));
    }

    private JSlider createSpeedSlider() {
        JSlider slider = new JSlider();
        slider.setMinimum(20);
        slider.setMaximum(500);
        slider.setValue(250);
        slider.setInverted(true);
        return slider;
    }

    private JButton createStartPauseButton() {
        JButton startPauseButton = new JButton("Start");
        startPauseButton.addActionListener(_ -> {
            if (simulationIsAutoRunning) {
                pauseSimulation();
                startPauseButton.setText("Resume");
            } else {
                resumeSimulation();
                startPauseButton.setText("Pause");
            }
        });
        return startPauseButton;
    }

    void launchWindow() {
        mainWindow.setLocationRelativeTo(null);
        mainWindow.requestFocus();
        mainWindow.toFront();
        mainWindow.setVisible(true);
    }
}
