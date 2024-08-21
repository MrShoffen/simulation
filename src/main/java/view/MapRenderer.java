package view;

import world.Map;

public abstract class MapRenderer {
    protected   Map map;

    protected int height;
    protected int width;

    public void setMap(Map map){
        this.map = map;
        this.height = map.getHeight();
        this.width = map.getWidth();
    }

    public abstract void render();
}
