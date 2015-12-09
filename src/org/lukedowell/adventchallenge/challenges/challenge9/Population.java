package org.lukedowell.adventchallenge.challenges.challenge9;

/**
 * Represents a population of tours.
 * Created by ldowell on 12/9/15.
 */
public class Population {

    Tour[] tours;

    public Population(int populationSize, boolean initialize) {
        tours = new Tour[populationSize];

        if(initialize) {
            for (int i = 0 ; i < tours.length; i++) {
                Tour newTour = new Tour();
                newTour.generateIndividual();
                saveTour(i, newTour);
            }
        }
    }

    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }

    public Tour getTour(int index) {
        return tours[index];
    }

    public Tour getFittest() {
        Tour fittest = tours[0];
        // Loop through individuals to find fittest
        for (int i = 1; i < tours.length; i++) {
            if (fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        return fittest;
    }

    public int getSize() {
        return tours.length;
    }
}
