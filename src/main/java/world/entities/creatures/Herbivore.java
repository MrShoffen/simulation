package world.entities.creatures;

import world.entities.Consumable;
import world.entities.environment.Grass;

import java.util.Random;

public final class Herbivore extends Creature implements Consumable {
    private static final int MINIMUM_HEALTH = 6;
    private static final int MAXIMUM_HEALTH = 10;

    private static final int MINIMUM_SPEED = 6;
    private static final int MAXIMUM_SPEED = 12;


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

    public static Herbivore randomHerbivore() {
        Random random = new Random();
        int randomHealth = random.nextInt(MINIMUM_HEALTH, MAXIMUM_HEALTH);
        int randomSpeed = random.nextInt(MINIMUM_SPEED, MAXIMUM_SPEED);
        return new Herbivore(randomHealth, randomSpeed);
    }

}
