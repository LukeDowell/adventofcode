package org.lukedowell.adventchallenge;

import org.lukedowell.adventchallenge.challenges.*;
import org.lukedowell.adventchallenge.challenges.challenge9.Challenge9;

/**
 *
 * http://adventofcode.com/
 *
 * Created by ldowell on 12/7/15.
 */
public class Main {

    public static void main(String[] args) {
        try {
            new Challenge9().process();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
