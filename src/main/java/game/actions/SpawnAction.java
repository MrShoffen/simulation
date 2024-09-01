package game.actions;

import world.Cell;
import world.GridMap;
import world.MapUtils;
import world.entities.Entity;
import world.entities.creatures.Herbivore;
import world.entities.creatures.Predator;
import world.entities.environment.Grass;
import world.entities.environment.Rock;
import world.entities.environment.Tree;

//todo refactor in one generic class
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
            Cell randomEmptyCell = MapUtils.randomEmptyCell(map);
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
                    case PREDATOR -> Predator.randomPredator();
                    case HERBIVORE -> Herbivore.randomHerbivore();
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
