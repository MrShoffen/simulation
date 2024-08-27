package view;

import world.GridMap;

public abstract class MapRenderer {
    protected GridMap map;

    protected int height;
    protected int width;

    public void setMap(GridMap map) {
        this.map = map;
        this.height = map.getHeight();
        this.width = map.getWidth();
    }

    public abstract void render();
}
