package org.lukedowell.adventchallenge.challenges;

import org.lukedowell.adventchallenge.ChallengeProcessor;

import java.io.File;

/**
 * Created by ldowell on 12/8/15.
 */
public class Challenge6 extends ChallengeProcessor{

    /** Our lighting grid */
    private int[][] light_grid = new int[1000][1000];

    public Challenge6() {
        super(new File("res/six.txt"));
    }

    @Override
    public void process() throws Exception {
        getInputStrings().forEach(input -> {

            // Split the input string into parseable portions
            String[] inputs = input.split(" ");

            // Switch the first verb
            switch(inputs[0].toLowerCase()) {
                case "turn":
                    if(inputs[1].equalsIgnoreCase("on")) {
                        turnOn(
                                getCoordinatesFromString(inputs[2]),
                                getCoordinatesFromString(inputs[4])
                        );
                    } else {
                        turnOff(
                                getCoordinatesFromString(inputs[2]),
                                getCoordinatesFromString(inputs[4])
                        );
                    }
                    break;
                case "toggle":
                    toggle(
                            getCoordinatesFromString(inputs[1]),
                            getCoordinatesFromString(inputs[3])
                    );
                    break;
            }
        });

        finish();
    }

    private void toggle(int[] coords1, int[] coords2) {
        for(int x = coords1[0]; x <= coords2[0]; x++) {
            for(int y = coords1[1]; y <= coords2[1]; y++) {
                light_grid[x][y] += 2;
            }
        }
    }

    private void turnOff(int[] coords1, int[] coords2) {
        for(int x = coords1[0]; x <= coords2[0]; x++) {
            for(int y = coords1[1]; y <= coords2[1]; y++) {
                if(light_grid[x][y] > 0) {
                    light_grid[x][y] -= 1;
                }
            }
        }
    }

    private void turnOn(int[] coords1, int[] coords2) {
        for(int x = coords1[0]; x <= coords2[0]; x++) {
            for(int y = coords1[1]; y <= coords2[1]; y++) {
                light_grid[x][y] += 1;
            }
        }
    }

    private int[] getCoordinatesFromString(String input) {
        String[] strCoords = input.split(",");
        int[] coords = new int[2];
        coords[0] = Integer.parseInt(strCoords[0]); // x
        coords[1] = Integer.parseInt(strCoords[1]); // y

        return coords;
    }

    private void finish() {
        int numLightsOn = 0;
        for(int x = 0; x < 1000; x++) {
            for(int y = 0; y < 1000; y++) {
                numLightsOn += light_grid[x][y];
            }
        }

        System.out.println("Number of lights on: " + numLightsOn);
    }
}
