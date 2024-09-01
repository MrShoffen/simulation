package world.entities.creatures;

import world.entities.Consumable;
import world.entities.environment.Grass;

public final class Herbivore extends Creature implements Consumable {
    public static final int MIN_HEALTH = 6;
    public static final int MAX_HEALTH = 10;

    public static final int MIN_SPEED = 6;
    public static final int MAX_SPEED = 12;

    private Herbivore(int health, int speed) {
        super(health, speed);
        victimClass = Grass.class;
    }

    @Override
    protected boolean attackVictim(Consumable victim) {
        heal(victim.healingPowerAfterDeath());
        return true;
    }

    @Override
    public int healingPowerAfterDeath() {
        return maxHealth / 2;
    }

    public static Herbivore newInstance(int health, int speed) {
        return new Herbivore(health, speed);
    }

}
