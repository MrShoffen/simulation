package game.actions;

import world.map.Cell;
import world.map.GridMap;
import world.entities.Entity;
import world.entities.creatures.Creature;
import world.entities.creatures.Herbivore;
import world.entities.creatures.Predator;
import world.entities.environment.Grass;
import world.entities.environment.Rock;
import world.entities.environment.Tree;

import java.util.Random;

public class SpawnAction extends Action {
    final TypeForSpawn typeForSpawn;

    public SpawnAction(GridMap map, TypeForSpawn type) {
        super(map);
        typeForSpawn = type;
    }

    @Override
    public final void perform() {
        int quantityForSpawn = getSpawnQuantity();

        for (int i = 0; i < quantityForSpawn; i++) {
            Cell randomEmptyCell = ActionUtils.randomEmptyCell(map);
            map.placeEntity(randomEmptyCell, randomEntity());
        }
    }

    private int getSpawnQuantity() {
        int mapSq = map.getHeight() * map.getWidth();
        int maxQuantityToSpawn = (int) (typeForSpawn.spawnRate() * mapSq);
        return (maxQuantityToSpawn - currentQuantityOfEntityType());
    }

    private int currentQuantityOfEntityType() {
        return (int) map.allEntities().stream()
                .filter(entity -> entity.getClass() == typeForSpawn.entityClass())
                .count();
    }

    private Entity randomEntity() {
        return
                switch (typeForSpawn) {
                    case ROCK -> new Rock();
                    case TREE -> new Tree();
                    case GRASS -> new Grass();
                    case PREDATOR, HERBIVORE -> randomCreature();
                };
    }

    private Creature randomCreature() {
        Random rand = new Random();
        return
                switch (typeForSpawn) {
                    case PREDATOR -> Predator.newInstance(rand.nextInt(Predator.MIN_HEALTH, Predator.MAX_HEALTH),
                            rand.nextInt(Predator.MIN_SPEED, Predator.MAX_SPEED),
                            rand.nextInt(Predator.MIN_ATTACK, Predator.MAX_ATTACK));
                    case HERBIVORE -> Herbivore.newInstance(rand.nextInt(Herbivore.MIN_HEALTH, Herbivore.MAX_HEALTH),
                            rand.nextInt(Herbivore.MIN_SPEED, Herbivore.MAX_SPEED));
                    default -> throw new IllegalStateException();
                };
    }

    public enum TypeForSpawn {
        ROCK(0.09, Rock.class),
        GRASS(0.06, Grass.class),
        TREE(0.08, Tree.class),
        PREDATOR(0.06, Predator.class),
        HERBIVORE(0.06, Herbivore.class);

        private final double spawnRate;
        private final Class<? extends Entity> entityClass;

        TypeForSpawn(double spawnRate, Class<? extends Entity> entityClass) {
            this.spawnRate = spawnRate;
            this.entityClass = entityClass;
        }

        private double spawnRate() {
            return spawnRate;
        }

        private Class<? extends Entity> entityClass() {
            return entityClass;
        }
    }
}
