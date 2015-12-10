package org.lukedowell.adventchallenge.challenges.challenge9;

import org.lukedowell.adventchallenge.ChallengeProcessor;
import org.lukedowell.adventchallenge.challenges.challenge9.gui.TourGuide;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ldowell on 12/9/15.
 */
public class Challenge9 extends ChallengeProcessor {

    public Challenge9() throws ClassNotFoundException {
        super(new File("res/tryhard.txt"));
        Class.forName("org.lukedowell.adventchallenge.challenges.challenge9.CityManager");
    }

    @Override
    public void process() throws Exception {
        getInputStrings().forEach(tour -> {
            String[] tour_data = tour.split(" ");
            int cityY = Integer.parseInt(tour_data[tour_data.length-1]);
            int cityX = Integer.parseInt(tour_data[tour_data.length-2]);

            String cityName = "";

            for(int i = 0 ; i < tour_data.length - 2; i++) {
                cityName += tour_data[i];
            }

            City newCity = new City(cityName, cityX, cityY);
            CityManager.addCity(newCity);
            System.out.println(newCity);
        });



        Population seed = new Population(50, true);
        EventQueue.invokeLater(TourGuide::new);

        // See what happens after 100 generations
        for(int i = 0; i < 50; i++) {
            seed = GeneticAlgorithm.evolvePopulation(seed);
            TourGuide.addTour(seed.getFittest());
        }
    }
}
