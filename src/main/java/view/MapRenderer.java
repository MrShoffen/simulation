package view;

import game.event.ListenerOfMapChange;
import world.map.GridMap;

public interface MapRenderer extends ListenerOfMapChange {
    void render(GridMap map);
}
