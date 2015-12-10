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

    /**
     * Creates a new city
     * @param name
     *      The name of the city
     */
    public City(String name) {
        this.name = name;
        routes = new HashMap<>();
    }

    /**
     * Adds a destination to this city
     * @param city
     *  The city to end at
     * @param distance
     *  The distance to the given city
     */
    public void addDestination(City city, int distance) {
        routes.put(city, distance);
    }

    /**
     * @param city
     *      A city object
     * @return
     *      The distance to the provided city
     */
    public int getDistanceToCity(City city) {
        if(routes == null || routes.get(city) == null) {
            System.err.println("wtf");
        }
        return routes.get(city);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        City otherCity = (City) obj;
        return otherCity.getName().equalsIgnoreCase(getName());
    }
}