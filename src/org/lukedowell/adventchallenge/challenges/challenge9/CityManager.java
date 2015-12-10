package org.lukedowell.adventchallenge.challenges.challenge9;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by ldowell on 12/9/15.
 */
public class CityManager {

    /** A collection of all cities available to tour */
    private static List<City> cities = new LinkedList<>();

    public static void addCity(City city) {
        cities.add(city);
    }

    public static City getCity(int index) {
        return cities.get(index);
    }

    public static int numberOfCities() {
        return cities.size();
    }

}
