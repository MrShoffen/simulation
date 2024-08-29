package view.swing;


import game.Simulation;
import view.SimulationController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingController extends SimulationController {

    SwingMapRenderer map;

    JFrame mainWindow;


    public SwingController(Simulation sim) {
        super(sim);
        initializeWindow();
    }

    private void initializeWindow() {
        this.map = (SwingMapRenderer) simulation.getRenderer();
        mainWindow = new JFrame("Simulation");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(false);

        JPanel controlPanel = createControlPanel();

        mainWindow.getContentPane().add(map, BorderLayout.WEST);
        mainWindow.getContentPane().add(controlPanel, BorderLayout.EAST);
        mainWindow.pack();

    }

    private JPanel createControlPanel() {
        // Создаем кнопку для обновления содержимого
        JButton startPauseButton = createStartPauseButton();
        JLabel sliderLabel = new JLabel("Скорость симуляции");
        JSlider slider = createSpeedSlider();

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        controlPanel.add(startPauseButton);
        controlPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        controlPanel.add(sliderLabel);
        controlPanel.add(slider);
        return controlPanel;
    }

    private  JSlider createSpeedSlider() {
        JSlider slider = new JSlider();
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                simulation.setMillisPause(slider.getValue());
            }
        });
        slider.setMinimum(50);
        slider.setMaximum(500);
        slider.setValue(250);
        slider.setInverted(true);
        return slider;
    }

    private JButton createStartPauseButton() {
        JButton startPauseButton = new JButton("Start");
        startPauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(simulation.isAutoRunning()) {
                    pause();
                    startPauseButton.setText("Resume");
                } else {
                 resume();
                 startPauseButton.setText("Pause");
                }
            }
        });
        return startPauseButton;
    }

    void launch() {
        mainWindow.setVisible(true);
        mainWindow.setLocationRelativeTo(null);
//        mainWindow.requestFocus();
        mainWindow.toFront();
    }

    @Override
    public void run() {
        launch();

        gameRunning = true;
        while (gameRunning) {

            if (simulation.isAutoRunning()) {
                try {
                    simulation.nextTurn();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
