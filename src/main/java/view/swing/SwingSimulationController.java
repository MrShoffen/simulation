package view.swing;


import game.Simulation;
import view.SimulationController;
import world.entities.creatures.Herbivore;
import world.entities.creatures.Predator;

import javax.swing.*;
import java.awt.*;

public class SwingSimulationController extends SimulationController {
    protected int delayTimeInMillis;

    JFrame mainWindow;
    private JLabel simCount;
    private JLabel predatorCount;
    private JLabel herbivoreCount;


    public SwingSimulationController(Simulation sim) {
        super(sim);
        delayTimeInMillis = 250;
        initializeWindow();
    }


    private void initializeWindow() {
        SwingMapRenderer map = (SwingMapRenderer) simulation.getRenderer();
        mainWindow = new JFrame("Simulation");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);
        mainWindow.setIconImage(Images.ICON);

        JPanel controlPanel = createControlPanel();
        mainWindow.getContentPane().add(map, BorderLayout.WEST);
        mainWindow.getContentPane().add(controlPanel, BorderLayout.EAST);
        mainWindow.pack();
    }

    private JPanel createControlPanel() {
        JButton startPauseButton = createStartPauseButton();
        JLabel sliderLabel = new JLabel("Скорость симуляции");
        JSlider slider = createSpeedSlider();
        simCount = new JLabel("Шаг симлуляции: " + simulation.getSimulationCount());
        predatorCount = new JLabel("Хищники: " + 12);
        herbivoreCount = new JLabel("Травоядные: " + 43);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        controlPanel.add(startPauseButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        controlPanel.add(sliderLabel);
        controlPanel.add(slider);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        controlPanel.add(simCount);
        controlPanel.add(predatorCount);
        controlPanel.add(herbivoreCount);
        return controlPanel;
    }

    private void updateSimulationStats() {
        simCount.setText("Шаг симлуляции: " + simulation.getSimulationCount());
        predatorCount.setText("Хищники: " + simulation.getEntityCountByType(Predator.class));
        herbivoreCount.setText("Травоядные: " + simulation.getEntityCountByType(Herbivore.class));
    }

    private JSlider createSpeedSlider() {
        JSlider slider = new JSlider();
        slider.addChangeListener(_ -> simulation.setMillisPauseBetweenMoves(slider.getValue()));
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
                pause();
                startPauseButton.setText("Resume");
            } else {
                resume();
                startPauseButton.setText("Pause");
            }
        });
        return startPauseButton;
    }

    void launch() {
        mainWindow.setLocationRelativeTo(null);
        mainWindow.requestFocus();
        mainWindow.toFront();
        mainWindow.setVisible(true);
    }

    @Override
    public void run() {
        launch();
        gameRunning = true;
        simulationIsAutoRunning = false;

        while (gameRunning) {
            if (simulationIsAutoRunning) {
                simulation.nextTurn();
            }
            updateSimulationStats();
        }
    }
}
