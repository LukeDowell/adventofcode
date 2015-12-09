package org.lukedowell.adventchallenge.challenges.challenge9;

import java.util.HashMap;

/**
 * Representation of an endpoint in our
 * traveling salesman problem
 */
public class City {

    /** The name of the city */
    private String name;

    /** A list of tours branching out from this city */
    private HashMap<City, Integer> routes;

    public City(String name) {
        this.name = name;
        routes = new HashMap<>();
    }

    public void addDestination(City city, int distance) {
        routes.put(city, distance);
    }

    public int getDistanceToCity(City city) {
        return routes.get(city);
    }

    public String getName() {
        return name;
    }
}