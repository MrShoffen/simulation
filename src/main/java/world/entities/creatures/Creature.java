package world.entities.creatures;

import game.search.BFSSearchStrategy;
import game.search.SearchStrategy;
import world.entities.Entity;
import world.map.Cell;
import world.map.GridMap;
import world.entities.Consumable;

import java.util.Optional;

public abstract class Creature extends Entity {
    protected Class<? extends Entity> victimClass;

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
        if (this.isDead() || !this.canMove) {
            return;
        }
        Optional<Cell> startOptCell = SearchStrategy.locateCellOfEntity(map, this);
        Cell startingCell = startOptCell.get();
        Cell nextCell = strategy.findNextCellToTarget(startingCell, map, victimClass);

        boolean victimIsConsumed = false;
        if (nextCellContainsEntityType(nextCell, victimClass, map)) {
            canMove = false;
            Consumable victim = (Consumable) map.getEntity(nextCell).get();
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

    public final Optional<Creature> findPartnerForBreed(GridMap map) {
        Cell currentCell = SearchStrategy.locateCellOfEntity(map, this).get();
        Cell cellWithMate = strategy.findNextCellToTarget(currentCell, map, this.getClass());
        if (cellWithMate.equals(currentCell)) {
            return Optional.empty();
        }
        if (nextCellContainsEntityType(cellWithMate,this.getClass(),map)) {
            return Optional.of((Creature) map.getEntity(cellWithMate).get());
        }
        return Optional.empty();
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

    private boolean nextCellContainsEntityType(Cell nextCell, Class<? extends Entity> type, GridMap map) {
        return map.getEntity(nextCell).isPresent() && map.getEntity(nextCell).get().getClass() == type;
    }

    public final int currentHealth() {
        return health;
    }

    public final int maxHealth() {
        return maxHealth;
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
