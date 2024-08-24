package world.entities.creatures;

import game.algoritms.BFSSearchStrategy;
import game.algoritms.SearchStrategy;
import world.Cell;
import world.Map;
import world.entities.Consumable;
import world.entities.Entity;

import java.util.Optional;

public abstract class Creature extends Entity {
    protected Class<? extends Consumable> VICTIM_CLASS;

    protected int health;
    protected final int maxHealth;
    protected final int speed;
    protected final SearchStrategy strategy;

    protected boolean canMove;
    protected int movesWithoutFood;

    protected Creature(int health, int speed) {
        this.maxHealth = health;
        this.health = maxHealth;
        this.speed = speed;
        strategy = new BFSSearchStrategy();
        canMove = true;
        movesWithoutFood = 0;
    }

    public int movesWithoutFood() {
        return movesWithoutFood;
    }

    public boolean canMove() {
        return canMove;
    }

    public void allowToMove() {
        canMove = true;
    }

    public void move(Map map) {
        Optional<Cell> start = map.locateCellOfCreature(this);
        if (this.isDead() || start.isEmpty() || !canMove) {
            return;
        }
        Cell startingCell = start.get();
        Cell nextCell = strategy.find(startingCell, VICTIM_CLASS);

        boolean victimIsConsumed = false;
        if (nextCellContainsVictim(nextCell)) {
            Consumable victim = (Consumable) nextCell.getEntity();
            victimIsConsumed = tryToConsume(victim);
            if (!victimIsConsumed) {
                movesWithoutFood++;
                return;
            }
        }

        if (victimIsConsumed) {
            movesWithoutFood = 0;
        } else {
            movesWithoutFood++;
        }
//        System.out.println("im moving, my speed is " + speed + " and my cell is " + startingCell + ", my next cell is " +nextCell + ", road to " + VICTIM_CLASS);
        map.moveEntity(startingCell, nextCell);
    }


    protected abstract boolean tryToConsume(Consumable victim);

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
        if (health < 0) {
            health = 0;
        }
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
