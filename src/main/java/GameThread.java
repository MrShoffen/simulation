import game.Simulation;

public class GameThread implements Runnable{
    Simulation simulation;

    boolean gameRunning;

    public GameThread(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public void run() {
        gameRunning = true;
        resume();
        while(gameRunning){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(simulation.isRunning()){
                try {
                    simulation.nextTurn();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Game.ASK_FOR_RUNNING_MENU_CHOICE + '\n');
            }
        }
    }

    public void pause(){
        if(simulation.isRunning()) {
            simulation.pause();
        }
    }
    public void resume(){
        if(!simulation.isRunning()) {
            simulation.resume();
        }
    }

    public void stop(){
        pause();
        gameRunning = false;
    }


}
