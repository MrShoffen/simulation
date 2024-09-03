package world.entities.creatures;

import world.entities.Consumable;

public final class Predator extends Creature {
    public static final int MIN_HEALTH = 5;
    public static final int MAX_HEALTH = 10;

    public static final int MIN_SPEED = 6;
    public static final int MAX_SPEED = 10;

    public static final int MIN_ATTACK = 4;
    public static final int MAX_ATTACK = 9;

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

    public static Predator newInstance(int health, int speed, int attack) {
        return new Predator(health, speed, attack);
    }

}
