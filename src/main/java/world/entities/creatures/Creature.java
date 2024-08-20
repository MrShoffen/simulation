package world.entities.creatures;

import game.algoritms.BFSSearchStrategy;
import game.algoritms.SearchStrategy;
import world.Cell;
import world.Map;
import world.entities.Entity;

public abstract class Creature extends Entity {

    private int health;
    //    private final int maxHealth;
    protected SearchStrategy strategy = new BFSSearchStrategy();


    public abstract void move(Map map);

    protected Cell findvictim() {
        SearchStrategy searchStrategy = new BFSSearchStrategy();
        return null;
    }


}
