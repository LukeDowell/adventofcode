package org.lukedowell.adventchallenge.challenges;

import org.lukedowell.adventchallenge.ChallengeProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by ldowell on 12/7/15.
 */
public class Challenge1 extends ChallengeProcessor{

    public Challenge1() {
        super(new File("res/one.txt"));
    }

    @Override
    public void process() throws Exception {
        int floor = 0;
        int firstBasementVisit = 0;
        boolean hasVisitedBasement = false;

        String input = new BufferedReader(new FileReader(inputFile)).readLine();

        for(int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if(character == ')') {
                floor--;
            } else {
                floor++;
            }

            if(floor < 0 && !hasVisitedBasement) {
                hasVisitedBasement = true;
                firstBasementVisit = i;
            }
        }

        System.out.println("The final floor is: " + floor);
        System.out.println("The character index that forced santa to visit the basement first is: " + (firstBasementVisit + 1)); // Plus one because programming indices start at 1 :^]

    }
}
