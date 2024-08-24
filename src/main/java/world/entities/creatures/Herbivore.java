package world.entities.creatures;

import world.entities.Consumable;
import world.entities.Entity;
import world.entities.objects.Grass;

public class Herbivore extends Creature implements Consumable {
    private static final int HEALTH_MULTIPLIER = 5;
    private static final int MINIMUM_HEALTH = 4;

    private static final int SPEED_MULTIPLIER = 4;
    private static final int MINIMUM_SPEED = 3;


    private Herbivore(int health, int speed) {
        super(health, speed);
        VICTIM_CLASS = Grass.class;
    }


    @Override
    protected boolean tryToConsume(Consumable victim) {
        canMove = false;
        heal(((Grass)victim).healingPowerAfterDeath());
        return true;
    }



    public static Herbivore randomHerbivore() {
        int randomHealth = (int)(Math.random()*HEALTH_MULTIPLIER) + MINIMUM_HEALTH;
        int randomSpeed = (int)(Math.random()*SPEED_MULTIPLIER) + MINIMUM_SPEED;
        return new Herbivore(randomHealth,randomSpeed);
    }

    @Override
    public int healingPowerAfterDeath() {
        return maxHealth/2;
    }
}
