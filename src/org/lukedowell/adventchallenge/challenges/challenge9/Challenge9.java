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

    public Challenge9() {
        super(new File("res/nine.txt"));
    }

    @Override
    public void process() throws Exception {
        getInputStrings().forEach(tour -> {
            String[] tour_data = tour.split(" ");

        });
    }
}
