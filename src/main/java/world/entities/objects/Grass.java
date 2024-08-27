package world.entities.objects;

import world.entities.Consumable;
import world.entities.Entity;

import java.util.Random;

public final class Grass extends Entity implements Consumable {
    private static final int MIN_GRASS_HEAL_VALUE = 1;
    private static final int MAX_GRASS_HEAL_VALUE = 4;

    @Override
    public int healingPowerAfterDeath() {
        return new Random().nextInt(MIN_GRASS_HEAL_VALUE,MAX_GRASS_HEAL_VALUE);
    }
}
