package org.lukedowell.adventchallenge.challenges;

import org.lukedowell.adventchallenge.ChallengeProcessor;

import java.io.File;
import java.util.regex.Matcher;

/**
 * Created by ldowell on 12/8/15.
 */
public class Challenge8 extends ChallengeProcessor {

    public Challenge8() {
        super(new File("res/eight.txt"));
    }

    /** The total number of code-represented characters in the given file */
    private int totalNumCodeChars = 0;

    /** The total number of characters held by the in-memory string values in the given file */
    private int totalNumMemoryChars = 0;

    @Override
    public void process() throws Exception {
        getInputStrings().forEach(string -> {
            // Find the number of characters in the code representation of the string
            int numCodeChars = string.length();
            int numMemoryChars = getNumMemoryChars(string);

            totalNumCodeChars += numCodeChars;
            totalNumMemoryChars += numMemoryChars;
        });

        System.out.println("The total number of CODE characters is : " + totalNumCodeChars);
        System.out.println("The total number of MEMORY characters is : " + totalNumMemoryChars);
        System.out.println("The difference is : " + (totalNumCodeChars - totalNumMemoryChars));
    }

    /**
     * Finds the number of characters that an in-memory string would have. As
     * an example, given the string "x", the number of in-memory characters would be one since
     * the quotations are removed. Given the string "aaa\"aaa", the number of in-memory
     * characters would be 7 since the quotations are removed and the escape characters count as
     * one.
     *
     * @param input
     *      The string to count
     * @return
     *      A number representing the number of in-memory characters the provided string would have.
     */
    private int getNumMemoryChars(String input) {

        // We have been promised that the only escape sequences we will encounter are \\, \", and \x**
        // We can replace all except the last sequence with any character since they will end up being a single character
        // The last one is trickier

        // Let's cut off the quotations on either side of the strings
        String quotesRemoved = input.substring(1, input.length() - 1);

        // Remove the backslash character. Since it is escaped, we need to do it twice. \\\\ = \\
        String escapesRemoved = quotesRemoved.replace("\\\"", "@")
                                                .replace("\\\\", "@");


        String hexRemoved = removeHexSequences(escapesRemoved);

        return hexRemoved.length();
    }

    /**
     * Removes all hex sequences (eg. \x12, \xab) from a given
     * string, leaving only the last character of the sequence behind.
     *
     * ab\x12ab\x12 would become ab2ab2
     *
     * @param input
     *      Provided string
     * @return
     *      Provided string without hex sequences
     */
    private String removeHexSequences(String input) {
        // Now we remove the hex escape sequence
        for(int i = 0; i < input.length(); i++) {

            // Get the character at the current index
            char c = input.charAt(i);

            // Check to see if it is a backslash
            if(c == '\\') {

                // We have captured the \, check to see if the previous character was an x
                if(input.charAt(i+1) == 'x') {

                    // Recursively remove all hex sequences
                    String replaced = input.replace(input.substring(i, i+3), "");
                    return removeHexSequences(replaced);
                }
            }
        }

        return input;
    }
}
