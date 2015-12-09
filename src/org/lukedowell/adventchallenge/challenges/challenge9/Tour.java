package org.lukedowell.adventchallenge.challenges.challenge9;


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
}