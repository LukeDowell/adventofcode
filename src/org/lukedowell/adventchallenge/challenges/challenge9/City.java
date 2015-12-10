package org.lukedowell.adventchallenge.challenges.challenge9;

import java.util.HashMap;

/**
 * Representation of an endpoint in our
 * traveling salesman problem
 */
public class City {

    /** The name of the city */
    private String name;

    private int x;
    private int y;

    /**
     * Creates a new city
     * @param name
     *      The name of the city
     */
    public City(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     * @param city
     *      A city object
     * @return
     *      The distance to the provided city
     */
    public double getDistanceToCity(City city) {
        int xDifference = city.getX() - getX();
        int yDifference = city.getY() - getY();

        return Math.sqrt((xDifference * xDifference + yDifference * yDifference));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {

        return getName() + " -- " + getX() + " , " + getY();
    }

    @Override
    public boolean equals(Object obj) {
        City otherCity = (City) obj;
        return otherCity.getName().equalsIgnoreCase(getName());
    }
}