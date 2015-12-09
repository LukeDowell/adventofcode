package org.lukedowell.adventchallenge.challenges.challenge9;

/**
 * Created by ldowell on 12/9/15.
 */
public class GeneticAlgorithm {

    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
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

            Tour child = crossover(parent1, parent2);

            newPop.saveTour(i, child);
        }

        for(int i = elitismOffset; i < newPop.getSize(); i++) {
            mutate(newPop.getTour(i));
        }

        return newPop;
    }

    public static Tour crossover(Tour parent1, Tour parent2) {
        //TODO: wtf is this shit clean it up
        Tour child = new Tour();

        int startPos = (int) (Math.random() * parent1.getSize());
        int endPos = (int) (Math.random() * parent1.getSize());

        for(int i = 0; i < child.getSize(); i++) {

            if(startPos < endPos && i > startPos && i < endPos) {
                child.setCity(i, parent1.getCity(i));
            } else if(startPos > endPos) {
                if(!(i < startPos && i > endPos)) {
                    child.setCity(i, parent1.getCity(i));
                }
            }
        }

        for(int i = 0; i < parent2.getSize(); i++) {

            if(!child.containsCity(parent2.getCity(i))) {
                for(int j = 0; j < child.getSize(); j++) {

                    if(child.getCity(j) == null) {
                        child.setCity(j, parent2.getCity(j));
                        break;
                    }
                }
            }
        }

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
