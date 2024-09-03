package view;

public interface Delayable {

    default void setDelayTime(int millis) {
    }

    default void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
