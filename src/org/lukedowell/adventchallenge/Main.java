package org.lukedowell.adventchallenge;

import org.lukedowell.adventchallenge.one.ChallengeOne;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by ldowell on 12/7/15.
 */
public class Main {

    public static void main(String[] args) {
        try {
            new ChallengeOne().process();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
