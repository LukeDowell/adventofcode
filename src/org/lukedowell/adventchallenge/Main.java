package org.lukedowell.adventchallenge;

import org.lukedowell.adventchallenge.challenges.*;

/**
 *
 * http://adventofcode.com/
 *
 * Created by ldowell on 12/7/15.
 */
public class Main {

    public static void main(String[] args) {
        try {
            new Challenge6().process();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
