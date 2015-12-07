package org.lukedowell.adventchallenge.challenges;

import org.lukedowell.adventchallenge.ChallengeProcessor;

import java.io.File;

/**
 * Created by ldowell on 12/7/15.
 */
public class Challenge5 extends ChallengeProcessor {

    /** The number of strings in the list that are nice */
    int numNiceStrings = 0;

    private static final String[] REGEX_RULES = {
            "[aeiouy]+", // TODO: Fix, needs to find at least 3 vowels
            "([a-z])\\1{1}", // Repeating characters (eg. gg, dd, tt)
            "^((?!(ab|cd|pq|xy)).)*$" // Doesn't contain ab, cd, pq or xy
    };

    public Challenge5() {
        super(null);
    }

    @Override
    public void process() throws Exception {

    }
}
