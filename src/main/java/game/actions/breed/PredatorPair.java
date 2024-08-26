package game.actions.breed;

import world.Cell;
import world.entities.creatures.Creature;
import world.entities.creatures.Predator;

 class PredatorPair extends Pair{
    protected PredatorPair(Cell firstParent, Cell secondParent) {
        super(firstParent, secondParent);
        breedControlChance = 0.15;
    }

    @Override
    Creature generateNewCreature() {
        return Predator.predatorWithSpecifiedStats(randomHealthOfParents(),randomSpeedOfParents(),randomAttackOfParents());
    }

    int randomAttackOfParents(){
        int firstAttack = ((Predator)firstParentCell.getEntity()).getAttack();
        int secondAttack = ((Predator)secondParentCell.getEntity()).getAttack();
        return randomInt(firstAttack,secondAttack);
    }
}
