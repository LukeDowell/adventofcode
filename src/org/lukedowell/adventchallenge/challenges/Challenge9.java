package org.lukedowell.adventchallenge.challenges;

import org.lukedowell.adventchallenge.ChallengeProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ldowell on 12/9/15.
 */
public class Challenge9 extends ChallengeProcessor {

    public Challenge9() {
        super(new File("res/nine.txt"));
    }

    @Override
    public void process() throws Exception {
        getInputStrings().forEach(tour -> {
            String[] tour_data = tour.split(" ");

        });
    }

    /**
     * Representation of an endpoint in our
     * traveling salesman problem
     */
    private class City {

        /** The name of the city */
        private String name;

        /** A list of tours branching out from this city */
        private List<Tour> tours;

        public City(String name) {
            this.name = name;
            tours = new ArrayList<>();
        }

        public void addTour(Tour t) {
            this.tours.add(t);
        }

        public int getDistanceToCity(City city) {
            for(Tour t : tours) {
                if(t.getDestination().getName().equalsIgnoreCase(city.getName())) {
                    return t.getDistance();
                }
            }
            return 9999;
        }

        public String getName() {
            return name;
        }

        public List<Tour> getTours() {
            return tours;
        }
    }

    /**
     * Represents a path between two cities.
     */
    private class Tour {

        /** The 'from' city */
        private City origin;

        /** The 'to' city */
        private City destination;

        /** The distance between the origin and the destination */
        private int distance;

        public Tour(City origin, City destination, int distance) {
            this.origin = origin;
            this.destination = destination;
            this.distance = distance;
        }

        public City getOrigin() {
            return origin;
        }

        public City getDestination() {
            return destination;
        }

        public int getDistance() {
            return distance;
        }
    }
}
