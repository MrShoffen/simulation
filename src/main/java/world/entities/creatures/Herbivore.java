package world.entities.creatures;

import world.entities.Entity;

public class Herbivore extends Creature {



    @Override
    protected boolean tryToConsume(Entity victim) {
        heal(victim.getHealingPower());
        return true;
    }
}
