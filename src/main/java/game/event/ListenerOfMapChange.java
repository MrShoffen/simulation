package game.event;

import world.map.GridMap;

public interface ListenerOfMapChange {
    void handleMapChange(GridMap map);
}
