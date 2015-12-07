package org.lukedowell.adventchallenge.challenges;

import org.lukedowell.adventchallenge.ChallengeProcessor;

import java.math.BigInteger;
import java.security.MessageDigest;


/**
 * Created by ldowell on 12/7/15.
 */
public class Challenge4 extends ChallengeProcessor {

    /** Secret key provided by advent site */
    private static final String SECRET_KEY = "ckczppom";

    public Challenge4() {
        super(null);
    }

    @Override
    public void process() throws Exception {

        // Set up the message digest to use MD5
        MessageDigest md = MessageDigest.getInstance("MD5");

        // Initialize the number we will append to our key
        int currentNumber = 0;

        // Initialize the concatenated string
        String toEnc = SECRET_KEY + currentNumber;

        // Update our digest with our string (no offset)
        md.update(toEnc.getBytes(), 0, toEnc.length());

        // Finish the digest and convert it to a 'readable' string
        String md5 = toHex(md.digest());

        while(!md5.substring(0, 6).equals("000000")) {
            // Repeeeeeat
            currentNumber++;
            toEnc = SECRET_KEY + currentNumber;
            md.update(toEnc.getBytes(), 0, toEnc.length());
            md5 = toHex(md.digest());
        }


        System.out.println(md5);
        System.out.println(currentNumber);
    }

    public static String toHex(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "X", bi);
    }
}
