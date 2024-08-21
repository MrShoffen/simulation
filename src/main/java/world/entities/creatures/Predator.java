package world.entities.creatures;

import world.entities.Entity;

public class Predator extends Creature{

    private int attack;

    public Predator() {
        super();
        attack = (int)(Math.random()*4)+4;
    }

    @Override
    protected boolean tryToConsume(Entity victim) {

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
}
