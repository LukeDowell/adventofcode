package org.lukedowell.adventchallenge.challenges;

import org.lukedowell.adventchallenge.ChallengeProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

/**
 * Created by ldowell on 12/9/15.
 */
public class Challenge9 extends ChallengeProcessor {

    private Collection<City> cities;

    public Challenge9() {
        super(new File("res/nine.txt"));
        cities = new ArrayList<>();
    }

    @Override
    public void process() throws Exception {
        getInputStrings().forEach(route -> {

            // 0 city name, 2 destination name, 4 distance
            String[] route_data = route.split(" ");

            // Pull our city objects
            City origin = findOrCreateCity(route_data[0]);
            City dest = findOrCreateCity(route_data[2]);

            // Parse the distance
            int distance = Integer.parseInt(route_data[4]);
        });
    }

    /**
     * Tries to find a city with a given name. If
     * none can be found, a city is created, added to our list,
     * and returned
     */
    private City findOrCreateCity(String name) {
        for(City city : cities) {
            if(city.name.equalsIgnoreCase(name)) {
                return city;
            }
        }
        City city = new City("name");
        cities.add(city);
        return city;
    }

    /**
     * A city is just a container for a bunch of vectors
     */
    private class City {

        private Collection<Vector> route_distances;
        private String name;

        public City(String name) {
            this.name = name;
        }
    }
}
