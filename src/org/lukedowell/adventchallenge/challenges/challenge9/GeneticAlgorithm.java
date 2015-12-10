package org.lukedowell.adventchallenge.challenges.challenge9;

/**
 * Created by ldowell on 12/9/15.
 */
public class GeneticAlgorithm {

    private static final double mutationRate = 0.2;
    private static final int tournamentSize = 10;
    private static final boolean elitism = true;

    public static Population evolvePopulation(Population pop) {
        Population newPop = new Population(pop.getSize(), false);

        int elitismOffset = 0;
        if(elitism) {
            newPop.saveTour(0, pop.getFittest());
            elitismOffset = 1;
        }

        for(int i = elitismOffset; i < newPop.getSize(); i++) {

            Tour parent1 = tournamentSelection(pop);
            Tour parent2 = tournamentSelection(pop);

            while(parent2.equals(parent1)) {
                parent2 = tournamentSelection(pop);
            }

            Tour child = crossover(parent1, parent2);

            newPop.saveTour(i, child);
        }

        for(int i = elitismOffset; i < newPop.getSize(); i++) {
            mutate(newPop.getTour(i));
        }

        return newPop;
    }

    /**
     * Breeds a child from two parent tours. Ordered crossover is
     * the goal, we will take a subset of routes from parent 1 and
     * insert them into the child. Then we will fill in remaining
     * destinations from parent 2 in the order that they are found
     * @param parent1
     * @param parent2
     * @return
     */
    public static Tour crossover(Tour parent1, Tour parent2) {
        //TODO: wtf is this shit clean it up
        Tour child = new Tour();

        // Find positions for the subsection to add to the child
        int startPos = (int) (Math.random() * parent1.getSize());
        int endPos = startPos;

        // Ensure they won't be the same value
        while(endPos == startPos) {
            endPos = (int) (Math.random() * parent1.getSize());
        }

        // Add the sub section to the child tour
        for(int i = 0 ; i < child.getSize(); i++) {

            // If the start position is less than the end position...
            if(startPos < endPos &&
                    i > startPos &&
                    i < endPos) {

                // Set the child city
                child.setCity(i, parent1.getCity(i));

            } else if(startPos > endPos &&
                        !(i < startPos && i > endPos)) { //wtf lee from theprojectspot
                child.setCity(i, parent1.getCity(i));
            }
        }

        // Loop through the second parents' tour
        for(int i = 0; i < parent2.getSize(); i++) {

            // Pull out the parents' city
            City parentCity = parent2.getCity(i);

            // If the child doesn't have the city, add it
            if(!child.containsCity(parentCity)) {

                // Loop to find the first open spot
                for(int j = 0; j < child.getSize(); j++) {
                    if(child.getCity(j) == null) {
                        child.setCity(j, parentCity);
                        break;
                    }
                }
            }
        }

        // Baby been born yo
        return child;
    }

    private static void mutate(Tour tour) {

        for(int tourPos1 = 0; tourPos1 < tour.getSize(); tourPos1++) {

            if(Math.random() <= mutationRate) {

                int tourPos2 = (int) (tour.getSize() * Math.random());

                City city1 = tour.getCity(tourPos1);
                City city2 = tour.getCity(tourPos2);

                tour.setCity(tourPos2, city1);
                tour.setCity(tourPos1, city2);
            }
        }
    }
    private static Tour tournamentSelection(Population pop) {

        Population tournament = new Population(tournamentSize, false);


        for(int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.getSize());
            tournament.saveTour(i, pop.getTour(randomId));
        }

        return tournament.getFittest();
    }
}
