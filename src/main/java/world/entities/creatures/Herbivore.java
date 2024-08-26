package world.entities.creatures;

import world.entities.Consumable;
import world.entities.objects.Grass;

public final class Herbivore extends Creature implements Consumable {
    private static final int HEALTH_MULTIPLIER = 4;
    private static final int MINIMUM_HEALTH = 6;

    private static final int SPEED_MULTIPLIER = 6;
    private static final int MINIMUM_SPEED = 6;


    private Herbivore(int health, int speed) {
        super(health, speed);
        victim_class = Grass.class;
    }

    @Override
    protected boolean tryToConsume(Consumable victim) {
        heal(victim.healingPowerAfterDeath());
        return true;
    }

    @Override
    public int healingPowerAfterDeath() {
        return maxHealth/2;
    }

    public static Herbivore randomHerbivore() {
        int randomHealth = (int)(Math.random()*HEALTH_MULTIPLIER) + MINIMUM_HEALTH;
        int randomSpeed = (int)(Math.random()*SPEED_MULTIPLIER) + MINIMUM_SPEED;
        return new Herbivore(randomHealth,randomSpeed);
    }

}
