package world.entities.objects;

import world.entities.Consumable;

public class Grass extends NonCreature implements Consumable {
    private static int MIN_GRASS_HEAL_VALUE = 1;
    private static int GRASS_HEAL_VALUE_MULTIPLIER = 3;
    @Override
    public int healingPowerAfterDeath() {
        return (int)(Math.random()*GRASS_HEAL_VALUE_MULTIPLIER) + MIN_GRASS_HEAL_VALUE;
    }
}
