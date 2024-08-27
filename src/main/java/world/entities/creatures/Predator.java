package world.entities.creatures;

import world.entities.Consumable;

public final class Predator extends Creature {
    private static final int HEALTH_MULTIPLIER = 4;
    private static final int MINIMUM_HEALTH = 6;

    private static final int SPEED_MULTIPLIER = 4;
    private static final int MINIMUM_SPEED = 6;

    private static final int ATTACK_MULTIPLIER = 5;
    private static final int MINIMUM_ATTACK = 4;

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
        int randomHealth = (int) (Math.random() * HEALTH_MULTIPLIER) + MINIMUM_HEALTH;
        int randomSpeed = (int) (Math.random() * SPEED_MULTIPLIER) + MINIMUM_SPEED;
        int randomAttack = (int) (Math.random() * ATTACK_MULTIPLIER) + MINIMUM_ATTACK;
        return new Predator(randomHealth, randomSpeed, randomAttack);
    }

}
