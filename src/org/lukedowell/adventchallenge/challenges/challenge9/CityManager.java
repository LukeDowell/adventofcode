package org.lukedowell.adventchallenge.challenges.challenge9;

import java.util.ArrayList;

/**
 * Created by ldowell on 12/9/15.
 */
public class CityManager {


    /** A collection of all cities available to tour */
    private static ArrayList<City> cities = new ArrayList<>();

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
