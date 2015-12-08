package org.lukedowell.adventchallenge.challenges;

import org.lukedowell.adventchallenge.ChallengeProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Created by ldowell on 12/8/15.
 */
public class Challenge7 extends ChallengeProcessor {

    /** Set of characters in our input file that designate the target of an operation */
    public static final String POINTER = "-> "; // We can remove this since it doesn't really provide any data. Also note the space at the end.

    /** Wires the the program has manipulated */
    public List<Wire> wires = new ArrayList<>();

    public Challenge7() {
        super(new File("res/seven.txt"));
    }

    @Override
    public void process() throws Exception {

        // My idea is to wire up the 'board', and once everything is wired to execute
        // But how to store directions inside each wire
        // And how to determine the root wires
        getInputStrings().forEach(line -> {
            String[] inputs = line.replaceAll(POINTER, "").split(" ");

            // The last element is always an identifier
            Wire childWire = findOrCreateWire(inputs[inputs.length -1]);

            // The line is a simple value assignment
            if(inputs.length <= 2) {
                try {
                    childWire.setSignal(Integer.parseInt(inputs[0]));
                } catch(NumberFormatException e) {
                    //dont caaaaare
                } finally {
                    childWire.setExpression(inputs[0]);
                }
            } else {

                // Deal with a NOT operator
                if(inputs[0].equalsIgnoreCase("NOT")) {
                    childWire.setExpression(inputs[0] + " " + inputs[1]);
                }

                // All other commands should be uniform
                childWire.setExpression(inputs[0] + " " + inputs[1] + " " + inputs[2]);
            }

            wires.add(childWire);
        });


        wires.forEach(parentWire -> {

            // If the wire has been given a value other than the default one
            if(parentWire.getSignal() != -9999) {
                System.out.println("Wire " + parentWire.getIdentifier() + " has a value of : " + parentWire.getSignal());
                System.out.println("The following wires rely on " + parentWire.getIdentifier());
                System.out.println("-------------------------------");
                for(Wire child : findWiresWithIdentifierInExpression(parentWire.getIdentifier())) {
                    System.out.println(child.getIdentifier() + " -- " + child.getExpression());
                }
                System.out.println("===============================");
            }
        });
    }

    /**
     * Attemps to find a wire in our 'pile'
     * @param identifier
     *      The case-insensitive name of the wire
     * @return
     *      A wire object, null if no wire was found
     */
    private Wire findOrCreateWire(String identifier) {
        for(Wire w : wires) {
            if(w.getIdentifier().equalsIgnoreCase(identifier)) {
                return w;
            }
        }
        return new Wire(identifier);
    }

    /**
     * Finds any and all wires that have the given identifier as a dependancy in
     * their expressions
     * @param identifier
     *      The identifier of the 'parent' wire
     * @return
     *      A list of all wires that rely on the given identifier
     */
    private List<Wire> findWiresWithIdentifierInExpression(String identifier) {
        List<Wire> dependants = new ArrayList<>();
        Pattern regex = Pattern.compile("\b" + identifier + "\b"); //TODO: wtf why doesn't this work
        for(Wire w : wires) {
            Matcher m = regex.matcher(w.getExpression());
            if(m.matches()) {
                dependants.add(w);
            }
        }
        return dependants;
    }

    /**
     * Represents a wire in bobby tables' electronics kit
     */
    private class Wire {

        private final String identifier;
        private int signal = -9999;
        private List<Wire> parents;
        private String expression;

        public Wire(String identifier) {
            this.identifier = identifier;
            parents = new ArrayList<>();
        }

        public String getIdentifier() {
            return identifier;
        }

        public int getSignal() {
            return signal;
        }

        public void setSignal(int signal) {
            this.signal = signal;
        }

        public List<Wire> getParents() {
            return parents;
        }

        public void addParent(Wire parent) {
            parents.add(parent);
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }
    }
}
