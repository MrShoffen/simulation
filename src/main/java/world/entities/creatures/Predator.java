package world.entities.creatures;

import world.entities.Entity;

public class Predator extends Creature{
    private static final int HEALTH_MULTIPLIER = 3;
    private static final int MINIMUM_HEALTH = 4;

    private static final int SPEED_MULTIPLIER = 4;
    private static final int MINIMUM_SPEED = 2;

    private static final int ATTACK_MULTIPLIER = 4;
    private static final int MINIMUM_ATTACK = 2;

    private final int attack;


    private Predator(int health, int speed, int attack) {
        super(health, speed);
        this.attack = attack;
        VICTIM_CLASS = Herbivore.class;
    }

    @Override
    protected boolean tryToConsume(Entity victim) {
        isMoving = false;

        Herbivore herbivore = (Herbivore) victim;
        herbivore.recieveDamage(this.attack);
        if(herbivore.isDead()){
            this.heal(herbivore.getHealingPower());
            return true;
        }
        return false;

    }

    public int getAttack() {
        return attack;
    }

    public static Predator randomPredator(){
        int randomHealth = (int)(Math.random()*HEALTH_MULTIPLIER) + MINIMUM_HEALTH;
        int randomSpeed = (int)(Math.random()*SPEED_MULTIPLIER) + MINIMUM_SPEED;
        int randomAttack = (int)(Math.random()*ATTACK_MULTIPLIER) + MINIMUM_ATTACK;
        return new Predator(randomHealth,randomSpeed,randomAttack);
    }
}
