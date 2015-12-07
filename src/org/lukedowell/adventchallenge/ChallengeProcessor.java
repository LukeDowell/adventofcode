package org.lukedowell.adventchallenge;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ldowell on 12/7/15.
 */
public abstract class ChallengeProcessor {

    protected final File inputFile;

    public ChallengeProcessor(File inputFile) {
        this.inputFile = inputFile;
    }

    public abstract void process() throws Exception;

    /**
     * If they are \n delimited
     */
    public List<String> getInputStrings() throws IOException {
        List<String> values = new LinkedList<>();
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String str;
        while((str = br.readLine()) != null) {
            values.add(str);
        }
        return values;
    }

    public String getInput() throws IOException {
        return new BufferedReader(new FileReader(inputFile)).readLine();
    }
}
