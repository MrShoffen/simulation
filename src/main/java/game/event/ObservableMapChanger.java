package game.event;

public interface ObservableMapChanger {

    void setListener(ListenerOfMapChange listener);

    void notifyListener();
}
