package org.lukedowell.adventchallenge.challenges.challenge9;

import org.lukedowell.adventchallenge.ChallengeProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ldowell on 12/9/15.
 */
public class Challenge9 extends ChallengeProcessor {

    List<City> cities;

    public Challenge9() {
        super(new File("res/nine.txt"));
        cities = new LinkedList<>();
    }

    @Override
    public void process() throws Exception {
        getInputStrings().forEach(tour -> {
            String[] tour_data = tour.split(" ");

            City origin = findOrCreate(tour_data[0]);
            City destination = findOrCreate(tour_data[2]);
            int distance = Integer.parseInt(tour_data[4]);

            origin.addDestination(destination, distance);
            destination.addDestination(origin, distance);
        });

        cities.forEach(CityManager::addCity);

        Population seed = new Population(15, true);
        System.out.println("Initial distance: " + seed.getFittest().getDistance());

        // See what happens after 100 generations
        for(int i = 0; i < 100; i++) {
            seed = GeneticAlgorithm.evolvePopulation(seed);
        }

        System.out.println("Final distance: " + seed.getFittest().getDistance());

        System.out.println(seed.getFittest());
    }

    City findOrCreate(String name) {
        for(City c : cities) {
            if(c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        City c = new City(name);
        cities.add(c);
        return c;
    }
}
