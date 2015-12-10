package org.lukedowell.adventchallenge.challenges.challenge9;

/**
 * Represents a path through all our possible destination
 * cities. Comprised of a collection of routes.
 */
public class Tour {

    /** An array list of cities that comprise the tour */
    private City[] tour;

    /** The fitness score of this tour */
    private double fitness = 0;

    /** The total distance this tour covers */
    private int distance = 0;

    /**
     * Creates a new tour and initializes the tour array
     */
    public Tour() {
        tour = new City[CityManager.numberOfCities()];
    }

    /**
     * Generates a route. This is done by pulling in every city and
     * shuffling them.
     */
    public void generateIndividual() {

        // Pull the cities in order
        for(int cityIndex = 0; cityIndex < CityManager.numberOfCities(); cityIndex++) {
            tour[cityIndex] = CityManager.getCity(cityIndex);
        }

        // Fisher yaatesss
        int numElements = tour.length;
        while(numElements > 0) {

            // Pick a random element
            int i = (int) Math.floor(Math.random() * numElements--);

            // Swap it with the current element
            City c1 = tour[numElements];
            tour[numElements] = tour[i];
            tour[i] = c1;
        }
    }


    /**
     * Generate an arbitrary fitness score. This challenge
     * wants the LEAST total distance, but if, for example,
     * you wanted the MOST distance you could change the / to a
     * *
     *
     * @return
     *      The fitness score this tour receives
     */
    public double getFitness() {
        if(fitness == 0) {
            fitness = 1 / (double) getDistance();
        }
        return fitness;
    }

    /**
     * @return
     *  The total distance covered by this tour=
     */
    public int getDistance() {
        if(distance == 0) {
            int tourDistance = 0;
            for(int i = 0; i < tour.length - 1; i++) {
                City origin = getCity(i);
                City dest = getCity(i + 1);

                if(dest == null) {
                    System.out.println("wtf again");
                }
                tourDistance += origin.getDistanceToCity(dest);
            }
            distance = tourDistance;
        }
        return distance;
    }

    /**
     * Checks to see whether or not this tour has a given city
     * @param city
     *      A city object
     * @return
     *      True if this tour contains the given city, false
     *      if not
     */
    public boolean containsCity(City city) {
        for(City c : tour) {
            if(c != null && c.equals(city)) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        return tour.length;
    }


    public City getCity(int index) {
        return tour[index];
    }

    public void setCity(int index, City city) {
        tour[index] = city;

        // Since we have modified the tour, these values are no longer accurate
        fitness = 0;
        distance = 0;
    }


    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < tour.length; i++) {
            geneString += getCity(i)+"|";
        }
        return geneString;
    }
}