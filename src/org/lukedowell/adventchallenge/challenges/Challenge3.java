package org.lukedowell.adventchallenge.challenges;

import org.lukedowell.adventchallenge.ChallengeProcessor;

import java.io.File;

/**
 * Created by ldowell on 12/7/15.
 */
public class Challenge3 extends ChallengeProcessor {

    // Directions
    private final static char NORTH = '^';
    private final static char SOUTH = 'v';
    private final static char WEST = '<';
    private final static char EAST = '>';

    // Position
    private static int loc_x = 5000;
    private static int loc_y = 5000;

    // Robo position
    private static int robo_loc_x = 5000;
    private static int robo_loc_y = 5000;

    // House grid
    private int[][] house_grid = new int[10000][10000];

    public Challenge3() {
        super(new File("res/three.txt"));
    }

    @Override
    public void process() throws Exception {

        // Deliver a starting gift
        house_grid[loc_x][loc_y]++;

        // Grab input
        String input = getInput();

        // Deliver all the presents
        for(int i = 0; i < input.length(); i++) {

            // Switch possible directions
            if(i % 2 == 0) {
                switch(input.charAt(i)) {
                    case NORTH:
                        loc_y++;
                        break;

                    case SOUTH:
                        loc_y--;
                        break;

                    case WEST:
                        loc_x--;
                        break;

                    case EAST:
                        loc_x++;
                        break;
                }
                // Deliver a present
                house_grid[loc_x][loc_y] += 1;
            } else {
                switch(input.charAt(i)) {
                    case NORTH:
                        robo_loc_y++;
                        break;

                    case SOUTH:
                        robo_loc_y--;
                        break;

                    case WEST:
                        robo_loc_x--;
                        break;

                    case EAST:
                        robo_loc_x++;
                        break;
                }
                // Deliver a present
                house_grid[robo_loc_x][robo_loc_y] += 1;
            }


        }

        // Count how many houses got at least one present
        int numHousesWithPresents = 0;
        for(int x = 0; x < 10000; x++) {
            for(int y = 0; y < 10000; y++) {
                if(house_grid[x][y] > 0) {
                    numHousesWithPresents++;
                }
            }
        }

        System.out.println("Number of houses with at least one present: " + numHousesWithPresents);
    }
}
