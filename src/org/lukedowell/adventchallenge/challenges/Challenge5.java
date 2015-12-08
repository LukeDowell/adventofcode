package org.lukedowell.adventchallenge.challenges;

import org.lukedowell.adventchallenge.ChallengeProcessor;

import java.io.File;
import java.util.regex.Pattern;

/**
 * TODO: finish
 * Created by ldowell on 12/7/15.
 */
public class Challenge5 extends ChallengeProcessor {

    /** The number of strings in the list that are nice */
    int numNiceStrings = 0;

    private static final String[] REGEX_RULES = {
            "([a-zA-Z])\\1{1}", // Repeating characters (eg. gg, dd, tt) //TODO: This doesn't appear to be working in practice, on regex101 it works fine
            "^((?!(ab|cd|pq|xy)).)*$" // Doesn't contain ab, cd, pq or xy
    };

    public Challenge5() {
        super(new File("res/five.txt"));
    }

    @Override
    public void process() throws Exception {
        getInputStrings().forEach(input -> {
            boolean hasFailed = false;
            for(String pattern : REGEX_RULES) {
                if(!input.matches(pattern)) {
                    hasFailed = true;
                }
            }

            if(!hasFailed) {
                // Check for three vowels
                int numVowels = input.length() - input.replaceAll("[aeiou]", "").length();
                if(numVowels >= 3) {
                    numNiceStrings++;
                }

            }
        });

        System.out.println("The number of nice strings is : " + numNiceStrings);
    }
}
