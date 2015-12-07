package org.lukedowell.adventchallenge.two;

import org.lukedowell.adventchallenge.ChallengeProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by ldowell on 12/7/15.
 */
public class ChallengeTwo extends ChallengeProcessor{

    public ChallengeTwo() {
        super(new File("res/two.txt"));
    }

    @Override
    public void process() throws Exception {

        // Loop through each line
        getInputStrings().forEach(input -> {

            // Turn our input string into dimentions
            String[] dimentions = input.split("x");
            int length = Integer.parseInt(dimentions[0]);
            int width = Integer.parseInt(dimentions[1]);
            int height = Integer.parseInt(dimentions[2]);

            // Calculate the area of paper needed


            // Add the slack
        });
    }
}
