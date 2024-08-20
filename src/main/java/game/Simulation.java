package game;

import world.Map;

import javax.swing.*;

public class Simulation {
    final private Map map;
    final private Renderer renderer;

    public Simulation(Map map, Renderer renderer) {
        this.map = map;
        this.renderer = renderer;
    }
}
