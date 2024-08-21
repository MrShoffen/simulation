package game.actions;

import world.Cell;
import world.Map;
import world.entities.Entity;

public abstract class spawnAction extends Action{
    int spawnRate;

    public spawnAction(Map map) {
        super(map);
    }

    @Override
    public void perform() {
//        getSpawnRate()
        int quantityForSpawn = getSpawnQuantity();

        for(int i = 0; i < quantityForSpawn; i++){
            getRandomEmptyCell().setEntity(randomEntity());
        }



//        place world.entities na kartu
    }

    abstract protected int getSpawnQuantity();
    abstract protected Entity randomEntity();

    private Cell getRandomEmptyCell(){
        Cell result;
        do{
            int x = (int) (Math.random()*map.getWidth());
            int y = (int) (Math.random()*map.getHeight());
            result = map.getCellAt(x,y);

        } while (result.hasEntity());

        return result;
    }

    protected int getNumberOfEntitiesByType(Class<? extends Entity> type){
        int result = 0;
        for (Entity entity : map.allEntities()) {
            if(entity.getClass() == type){
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        spawnAction a = new PredatorSpawnAction(new Map(5,10));
        a.getRandomEmptyCell();
    }

}
