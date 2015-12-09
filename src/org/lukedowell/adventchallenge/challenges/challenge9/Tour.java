package org.lukedowell.adventchallenge.challenges.challenge9;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a path through all our possible destination
 * cities. Comprised of a collection of routes.
 */
public class Tour {

    private List<City> tour;

    private double fitness = 0;
    private int distance = 0;

    public Tour() {
        tour = new LinkedList<>();
        for(int i = 0; i < CityManager.numberOfCities(); i++) {
            tour.add(null);
        }
    }

    public void generateIndividual() {
        for(int cityIndex = 0; cityIndex < CityManager.numberOfCities(); cityIndex++) {
            setCity(cityIndex, CityManager.getCity(cityIndex));
        }

        Collections.shuffle(tour);
    }

    public City getCity(int index) {
        return tour.get(index);
    }

    public void setCity(int index, City city) {
        tour.set(index, city);

        // Since we have modified the tour, these values are no longer accurate
        fitness = 0;
        distance = 0;
    }

    public double getFitness() {
        if(fitness == 0) {
            fitness = 1 / (double) getDistance();
        }
        return fitness;
    }

    public int getDistance() {
        if(distance == 0) {
            int tourDistance = 0;

            for(int cityIndex = 0; cityIndex < tour.size(); cityIndex++) {
                City origin = getCity(cityIndex);

                if(cityIndex+1 < tour.size()) {
                    City destination = getCity(cityIndex+1);
                    tourDistance += origin.getDistanceToCity(destination);
                }


            }
            distance = tourDistance;
        }
        return distance;
    }

    public boolean containsCity(City city) {
        return tour.contains(city);
    }

    public int getSize() {
        return tour.size();
    }

    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < tour.size(); i++) {
            geneString += getCity(i)+"|";
        }
        return geneString;
    }
}