package game.actions.breed;

import world.Cell;
import world.entities.creatures.Creature;
import world.entities.creatures.Predator;

 class PredatorPair extends Pair{
    protected PredatorPair(Cell firstParent, Cell secondParent) {
        super(firstParent, secondParent);
        breedControlChance = 0.1;
    }

    @Override
    Creature generateNewCreature() {
        return Predator.randomPredator();
    }

}
