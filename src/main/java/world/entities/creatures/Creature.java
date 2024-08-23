package world.entities.creatures;

import game.algoritms.BFSSearchStrategy;
import game.algoritms.SearchStrategy;
import world.Cell;
import world.Map;
import world.entities.Entity;

import java.util.Optional;

public abstract class Creature extends Entity {
    protected Class<? extends Entity> VICTIM_CLASS;

    protected int health;
    protected final int maxHealth;
    protected int speed;
    protected SearchStrategy strategy;

    protected boolean isMoving;

    protected Creature(int health, int speed) {
        this.maxHealth = health;
        this.health = maxHealth;
        this.speed = speed;
        strategy = new BFSSearchStrategy();
        isMoving = true;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void allowToMove(){
        isMoving = true;
    }

    public void move(Map map) {
        Optional<Cell> start = map.locateCellOfCreature(this);
        if (this.isDead() || start.isEmpty() || !isMoving) {
            return;
        }
        Cell startingCell = start.get();
        Cell nextCell = strategy.find(startingCell, VICTIM_CLASS);

        if (nextCellContainsVictim(nextCell)) {
            boolean successfullyConsumed = tryToConsume(nextCell.getEntity());
            if (!successfullyConsumed) {
                return;
            }
        }
//        System.out.println("im moving, my speed is " + speed + " and my cell is " + startingCell + ", my next cell is " +nextCell + ", road to " + VICTIM_CLASS);
        map.moveEntity(startingCell, nextCell);


//        map.move

    }


    protected abstract boolean tryToConsume(Entity victim);

    protected boolean nextCellContainsVictim(Cell nextCell) {
        return nextCell.hasEntity() && nextCell.getEntity().getClass() == VICTIM_CLASS;
    }

    public int currentHealth() {
        return health;
    }

    public int maxHealth() {
        return maxHealth;
    }

    public void recieveDamage(int damage) {
        health -= damage;
    }

    public void heal(int heal) {
        health += heal;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public boolean isDead() {
        return health <= 0;
    }

    public int speed() {
        return speed;
    }
}
