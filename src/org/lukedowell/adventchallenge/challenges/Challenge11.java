package org.lukedowell.adventchallenge.challenges;

import org.lukedowell.adventchallenge.ChallengeProcessor;


/**
 *
 * For example:
 *
 * hijklmmn meets the first requirement (because it contains the straight hij) but fails the second requirement requirement (because it contains i and l).
 * abbceffg meets the third requirement (because it repeats bb and ff) but fails the first requirement.
 * abbcegjk fails the third requirement, because it only has one double letter (bb).
 * The next password after abcdefgh is abcdffaa.
 * The next password after ghijklmn is ghjaabcc, because you eventually skip all the passwords that start with ghi..., since i is not allowed.
 *
 * Created by ldowell on 12/11/15.
 */
public class Challenge11 extends ChallengeProcessor {

    /** Puzzle seed */
    public static final String SEED = "hepxxyzz";

    /** Alphabeeeeet */
    private char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public Challenge11() {
        super(null);
    }

    @Override
    public void process() throws Exception {
        String password = incrementString(SEED); // Part two complete

        while(!meetsPasswordRequirements(password)) {
            password = incrementString(password);
        }

        System.out.println("Password meets requirements! Password is : " + password);
    }

    /**
     * Checks to see if the given password meets all requirements:

     *
     * @param password
     *     The given password to check
     * @return
     *      True if the given string passes all requirements
     */
    private boolean meetsPasswordRequirements(String password) {
        return
                hasIncreasingStraight(password) &&
                hasDiscretePairs(password) &&
                !containsBannedCharacters(password);
    }

    /**
     *
     * Passwords must include one increasing straight of at least three letters, like abc, bcd, cde, and so on, up to
     * xyz. They cannot skip letters; abd doesn't count.
     *
     * @param password
     *      The password to check
     * @return
     */
    private boolean hasIncreasingStraight(String password) {

        // Loop through each letter
        for( int i = 0; i < password.length(); i++) {

            // The character that might begin the series
            char seriesChar = password.charAt(i);

            // The index of the previous character in the series
            int lastIndex = indexOf(seriesChar);

            // The total length of the series
            int seriesLength = 1;

            // Loop ahead and see if the password contains a series
            for(int j = i + 1; j < password.length(); j++) {

                // Pull out the next character and grab its index
                char nextChar = password.charAt(j);
                int nextIndex = indexOf(nextChar);

                // If it is the next character in the series, increase the series length by one and store the new index
                if(lastIndex + 1 == nextIndex) {
                    seriesLength++;
                    lastIndex = nextIndex;
                } else {
                    // If the series is broken, leave the loop
                    break;
                }

            }

            // If we met the conditions, return true
            if(seriesLength >= 3) {
                return true;
            }
        }

        return false;
    }

    /**
     * Passwords must contain at least two different, non-overlapping pairs of letters, like aa, bb, or zz.
     * @param password
     * @return
     */
    private boolean hasDiscretePairs(String password) {

        int firstPairStartingIndex = -1;
        int secondPairStartingIndex = -1;

        // Find the first pair
        for(int i = 0; i < password.length() - 1; i++) {

            // Grab the current and next character
            char currentChar = password.charAt(i);
            char nextChar = password.charAt(i + 1);

            // If the two characters match
            if(currentChar == nextChar) {

                // The first pair has already been set
                if(firstPairStartingIndex > -1) {

                    // Check to see if we are far enough away (ie. not overlapping)
                    if(i - firstPairStartingIndex >= 2) {
                        secondPairStartingIndex = i;
                    }

                } else {
                    // The first pair has not been set
                    firstPairStartingIndex = i;
                }
            }
        }

        return
                firstPairStartingIndex > -1 &&
                secondPairStartingIndex > -1;
    }

    /**
     * Passwords may not contain the letters i, o, or l, as these letters can be mistaken for other characters
     * and are therefore confusing.
     *
     * @param password
     * @return
     */
    private boolean containsBannedCharacters(String password) {
        return
                password.contains("i") ||
                password.contains("o") ||
                password.contains("l");
    }

    /**
     * Increments a string.
     * @param input
     * @return
     */
    private String incrementString(String input) {
        // Return string
        StringBuilder output = new StringBuilder(input);

        // Whether or not the last character turn to Z.
        boolean wrapped = false;

        for(int i = input.length() - 1; i >= 0; i--) {

            // The current character
            char currentChar = input.charAt(i);
            char nextChar = currentChar;

            // If we are at the last character or if wrapped is true
            if(i == input.length() - 1 || wrapped) {

                // Increment the current character
                nextChar = incrementChar(currentChar);

                // If it didn't wrap, set wrapped to false
                wrapped = nextChar == 'a';
            }

            output.setCharAt(i, nextChar);
        }

        return output.toString();
    }

    /**
     * Increments a single character. A becomes b, c becomes d.
     * Z wraps around and becomes A
     * @param c
     *      The character to increment
     * @return
     */
    private char incrementChar(char c) {
        int nextIndex = indexOf(c) + 1;

        if(nextIndex >= alphabet.length) {
            nextIndex = 0;
        }
        return alphabet[nextIndex];
    }

    private int indexOf(char c) {
        for(int i = 0; i < alphabet.length; i++) {
            if(alphabet[i] == c) {
                return i;
            }
        }
        return -1;
    }
}
