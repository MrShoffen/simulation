package game.actions.breed;

import world.Cell;
import world.entities.creatures.Creature;
import world.entities.creatures.Herbivore;

 class HerbivorePair extends Pair{
        private final static double HERBIVORE_BREED_RATE = 0.27;

    HerbivorePair(Cell firstParent, Cell secondParent) {
        super(firstParent, secondParent);
        chanceOfThisCreatureTypeToBreed = HERBIVORE_BREED_RATE;
    }

    @Override
    Creature breedNewCreature() {
        return Herbivore.randomHerbivore();
    }
}
