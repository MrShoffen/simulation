package game.actions.breed;

import world.Cell;
import world.entities.creatures.Creature;
import world.entities.creatures.Herbivore;

 class HerbivorePair extends Pair{

    HerbivorePair(Cell firstParent, Cell secondParent) {
        super(firstParent, secondParent);
        breedControlChance = 0.27;
    }

    @Override
    Creature generateNewCreature() {
        return Herbivore.randomHerbivore();
    }
}
