package world.entities.creatures;

import world.entities.Consumable;

import java.util.Random;

public final class Predator extends Creature {
    private static final int MINIMUM_HEALTH = 5;
    private static final int MAXIMUM_HEALTH = 10;

    private static final int MINIMUM_SPEED = 6;
    private static final int MAXIMUM_SPEED = 10;

    private static final int MINIMUM_ATTACK = 4;
    private static final int MAXIMUM_ATTACK = 9;

    private final int attack;


    private Predator(int health, int speed, int attack) {
        super(health, speed);
        this.attack = attack;
        victimClass = Herbivore.class;
    }

    @Override
    protected boolean attackVictim(Consumable victim) {
        Herbivore herbivore = (Herbivore) victim;
        herbivore.receiveDamage(this.attack);

        if (herbivore.isDead()) {
            this.heal(herbivore.healingPowerAfterDeath());
            return true;
        }
        return false;
    }

    public int getAttack() {
        return attack;
    }

    public static Predator randomPredator() {
        Random random = new Random();
        int randomHealth = random.nextInt(MINIMUM_HEALTH,MAXIMUM_HEALTH);
        int randomSpeed = random.nextInt(MINIMUM_SPEED,MAXIMUM_SPEED);
        int randomAttack = random.nextInt(MINIMUM_ATTACK,MAXIMUM_ATTACK);
        return new Predator(randomHealth, randomSpeed, randomAttack);
    }

}
