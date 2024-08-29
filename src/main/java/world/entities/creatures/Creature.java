package world.entities.creatures;

import game.algoritms.BFSSearchStrategy;
import game.algoritms.SearchStrategy;
import world.Cell;
import world.GridMap;
import world.entities.Consumable;
import world.entities.Entity;

import java.util.Optional;

public abstract class Creature extends Entity {
    protected Class<? extends Consumable> victimClass;

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

    public final void move(GridMap map) {
        Optional<Cell> startOptCell = map.locateCellOfEntity(this);
        if (this.isDead() || startOptCell.isEmpty() || !canMove) {
            return;
        }
        Cell startingCell = startOptCell.get();
        Cell nextCell = strategy.find(startingCell, victimClass);


        boolean victimIsConsumed = false;
        if (nextCellContainsVictim(nextCell)) {
            canMove = false;
            Consumable victim = (Consumable) nextCell.getEntity();
            victimIsConsumed = attackVictim(victim);
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

        map.moveEntity(startingCell, nextCell);
    }

    public final int movesWithoutFood() {
        return movesWithoutFood;
    }

    public final boolean canMove() {
        return canMove;
    }

    public final void allowToMove() {
        canMove = true;
    }

    protected abstract boolean attackVictim(Consumable victim);

     private boolean nextCellContainsVictim(Cell nextCell) {
        return nextCell.hasEntity() && nextCell.getEntity().getClass() == victimClass;
    }

    public final int currentHealth() {
        return health;
    }

    public final int maxHealth() {
        return maxHealth;
    }

    public final boolean isFullHealed(){
         return health == maxHealth;
    }

    public final void receiveDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public final void heal(int heal) {
        health += heal;
        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public final boolean isDead() {
        return health <= 0;
    }

    public final int speed() {
        return speed;
    }
}
