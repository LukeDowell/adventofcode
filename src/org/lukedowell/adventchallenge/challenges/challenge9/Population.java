package org.lukedowell.adventchallenge.challenges.challenge9;

/**
 * Represents a population of tours.
 * Created by ldowell on 12/9/15.
 */
public class Population {

    /** The set of tours that belong to this population */
    private Tour[] tours;

    /**
     * Creates a new population
     * @param populationSize
     *      The amount of tours we want this population to have
     * @param initialize
     *      Whether or not we should ask the tours to initialize themselves
     */
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

    /**
     * Returns the tour with the highest
     * fitness score in this population
     * @return
     *      A tour
     */
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

    /**
     * Saves a tour at a given index
     * @param index
     *      The index to place the tour at
     * @param tour
     *      The tour to save
     */
    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }

    public Tour getTour(int index) {
        return tours[index];
    }



    public int getSize() {
        return tours.length;
    }
}
