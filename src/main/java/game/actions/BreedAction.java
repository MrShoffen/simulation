package game.actions;

import world.entities.creatures.Herbivore;
import world.entities.creatures.Predator;
import world.map.Cell;
import world.map.GridMap;
import world.entities.creatures.Creature;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public final class BreedAction extends Action {
    public BreedAction(GridMap map) {
        super(map);
    }

    @Override
    public void perform() {
        List<Creature> allCreatures = ActionUtils.allCreaturesFromMap(map);

        List<PairType> pairsReadyForBreed = new ArrayList<>();
        while (!allCreatures.isEmpty()) {
            Creature creature = allCreatures.getFirst();
            Optional<Creature> possiblePartnerForBreed = creature.findPartnerForBreed(map);
            if (possiblePartnerForBreed.isPresent()) {
                pairsReadyForBreed.add(PairType.createPair(creature));
                allCreatures.remove(possiblePartnerForBreed.get());
            }
            allCreatures.remove(creature);
        }

        Random random = new Random();
        for (PairType pair : pairsReadyForBreed) {
            if (random.nextDouble() <= pair.breedRate()) {
                Cell randomCell = ActionUtils.randomEmptyCell(map);
                map.placeEntity(randomCell, pair.breedCreature());
            }
        }
    }

    private enum PairType {
        PREDATOR_PAIR(0.21, SpawnAction::randomPredator),
        HERBIVORE_PAIR(0.33, SpawnAction::randomHerbivore);

        private final double breedRate;
        private final Supplier<Creature> creator;

        PairType(double breedRate, Supplier<Creature> creator) {
            this.breedRate = breedRate;
            this.creator = creator;
        }

        double breedRate() {
            return breedRate;
        }

        static PairType createPair(Creature creature) {
            return switch (creature) {
                case Predator _ -> PREDATOR_PAIR;
                case Herbivore _ -> HERBIVORE_PAIR;
                default -> throw new IllegalStateException();
            };
        }

        Creature breedCreature() {
            return creator.get();
        }
    }
}
