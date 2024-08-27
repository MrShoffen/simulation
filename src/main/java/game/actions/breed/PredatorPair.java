package game.actions.breed;

import world.Cell;
import world.entities.creatures.Creature;
import world.entities.creatures.Predator;

 class PredatorPair extends Pair{
     private final static double PREDATOR_BREED_RATE = 0.11;

    protected PredatorPair(Cell firstParent, Cell secondParent) {
        super(firstParent, secondParent);
        chanceOfThisCreatureTypeToBreed = PREDATOR_BREED_RATE;
    }

    @Override
    Creature breedNewCreature() {
        return Predator.randomPredator();
    }

}
