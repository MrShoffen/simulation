package world.entities.objects;

import world.entities.Consumable;
import world.entities.Entity;

public final class Grass extends Entity implements Consumable {
    private static final int MIN_GRASS_HEAL_VALUE = 1;
    private static final int GRASS_HEAL_VALUE_MULTIPLIER = 3;

    @Override
    public int healingPowerAfterDeath() {
        return (int) (Math.random() * GRASS_HEAL_VALUE_MULTIPLIER) + MIN_GRASS_HEAL_VALUE;
    }
}
