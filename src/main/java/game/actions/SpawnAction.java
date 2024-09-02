package game.actions;

import world.map.Cell;
import world.map.GridMap;
import world.entities.Entity;
import world.entities.creatures.Herbivore;
import world.entities.creatures.Predator;
import world.entities.environment.Grass;
import world.entities.environment.Rock;
import world.entities.environment.Tree;

import java.util.Random;
import java.util.function.Supplier;

public class SpawnAction extends Action {
    final EntityType entityType;

    public SpawnAction(GridMap map, EntityType type) {
        super(map);
        this.entityType = type;
    }

    @Override
    public final void perform() {
        int quantityForSpawn = getSpawnQuantity();

        for (int i = 0; i < quantityForSpawn; i++) {
            Cell randomEmptyCell = ActionUtils.randomEmptyCell(map);
            map.placeEntity(randomEmptyCell, entityType.newInstance());
        }
    }

    private int getSpawnQuantity() {
        int mapSq = map.getHeight() * map.getWidth();
        int maxQuantityToSpawn = (int) (entityType.spawnRate() * mapSq);
        return (maxQuantityToSpawn - currentQuantityOfEntityType());
    }

    private int currentQuantityOfEntityType() {
        return (int) map.allEntities().stream()
                .filter(entity -> entity.getClass() == entityType.entityClass())
                .count();
    }

    public static Herbivore randomHerbivore() {
        Random rand = new Random();
        return Herbivore.newInstance(rand.nextInt(Herbivore.MIN_HEALTH, Herbivore.MAX_HEALTH),
                rand.nextInt(Herbivore.MIN_SPEED, Herbivore.MAX_SPEED));
    }

    public static Predator randomPredator() {
        Random rand = new Random();
        return Predator.newInstance(rand.nextInt(Predator.MIN_HEALTH, Predator.MAX_HEALTH),
                rand.nextInt(Predator.MIN_SPEED, Predator.MAX_SPEED),
                rand.nextInt(Predator.MIN_ATTACK, Predator.MAX_ATTACK));
    }

    public enum EntityType {
        ROCK(0.09, Rock.class, Rock::new),
        GRASS(0.06, Grass.class,Grass::new),
        TREE(0.08, Tree.class,Tree::new),
        PREDATOR(0.06, Predator.class,SpawnAction::randomPredator),
        HERBIVORE(0.06, Herbivore.class,SpawnAction::randomHerbivore);

        private final double spawnRate;
        private final Class<? extends Entity> entityClass;
        private final Supplier<Entity> creator;

        EntityType(double spawnRate, Class<? extends Entity> entityClass, Supplier<Entity> creator) {
            this.spawnRate = spawnRate;
            this.entityClass = entityClass;
            this.creator = creator;
        }
        private Entity newInstance(){
            return creator.get();
        }

        private double spawnRate() {
            return spawnRate;
        }

        private Class<? extends Entity> entityClass() {
            return entityClass;
        }
    }
}
