package org.lukedowell.adventchallenge.challenges;

import org.lukedowell.adventchallenge.ChallengeProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ldowell on 12/7/15.
 */
public class Challenge2 extends ChallengeProcessor{

    int totalSquareFeet = 0; // Total square feet of wrapping paper needed
    int totalRibbonInFeet = 0; //The amount of ribbon we need in number of feet

    public Challenge2() {
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
            List<Integer> calculatedSides = Arrays.asList(
                    (2 * length * width),
                    (2 * width * height),
                    (2 * height * length)
            );

            int squareFootage = 0;
            for(int i : calculatedSides) {
                squareFootage += i;
            }
            int min = Collections.min(calculatedSides) / 2;

            // Footage and minimum
            totalSquareFeet += (squareFootage + min);

            // Bow
            int ribbonLength = getSmallestPerimeter(length, width, height); //Smallest perimeter of any face
            int ribbonBowLength = length * width * height; // Volume
            totalRibbonInFeet += (ribbonLength + ribbonBowLength);
        });

        System.out.println("Total area of wrapping paper needed: " + totalSquareFeet);
        System.out.println("The length of ribbon needed is: " + totalRibbonInFeet + " feet.");
    }

    private int getSmallestPerimeter(int length, int width, int height) {
        // Calculate all sides and place them in an array
        List<Integer> calculatedPerimeter = Arrays.asList(
                (width * 2 + height * 2),
                (length * 2 + height * 2),
                (length * 2 + width * 2)
        );
        return Collections.min(calculatedPerimeter);
    }
}
