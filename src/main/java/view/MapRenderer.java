package view;

import game.event.ListenerOfMapChange;
import world.map.GridMap;

public interface MapRenderer extends ListenerOfMapChange, Delayable {

    void render(GridMap map);

    @Override
    default void handleMapChange(GridMap map) {
        render(map);
    }
}
