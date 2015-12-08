package org.lukedowell.adventchallenge.challenges;

import org.lukedowell.adventchallenge.ChallengeProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

        // Execuuuuute
        while(findOrCreateWire("a").getSignal() == null) {
            wires.forEach(wire -> {

                // If this wire has a signal
                if(wire.getSignal() != null) {

                    // Try to populate the signal of it's dependants
                    execute(wire);
                }
            });
        }

        System.out.println("Wire a has signal: " + findOrCreateWire("a").getSignal());
    }

    /**
     * Meet and poa tay toes
     */
    private void execute(Wire w) {
        findWiresWithIdentifierInExpression(w.getIdentifier())
                .forEach(wire -> {
                    // For each child, attempt to calculate their signal

                    // Grab the inputs
                    String[] inputs = wire.getExpression().split(" ");

                    // Stupid not
                    if(inputs[0].equalsIgnoreCase("NOT")) {

                        // Since NOT only has one other wire, we know it's the provided wire
                        wire.setSignal(~ w.getSignal()); //wow i hope this is NOT

                    } else if(inputs.length == 1) {
                        // Simple assignment

                        wire.setSignal(w.getSignal());

                    } else {

                        /**
                         * There is a problem here. input[0] and input[2] are, at their core,
                         * just values that we are going to mash together with whatever
                         * bitwise operator is requested. The issue is that each index can
                         * have either a Wire identifier or a numeric value.
                         */

                        Integer firstValue = 0;
                        Integer secondValue = 0;

                        // Lets try parsing them as integers and if that fails, then assume its an identifier

                        try {
                            firstValue = Integer.parseInt(inputs[0]);
                        } catch(NumberFormatException e) {
                            firstValue = findOrCreateWire(inputs[0]).getSignal();
                        }

                        // CODE REPETITION
                        // CODE IS WET AS FUCK
                        try {
                            secondValue = Integer.parseInt(inputs[2]);
                        } catch(NumberFormatException e) {
                            secondValue = findOrCreateWire(inputs[2]).getSignal();
                        } catch(Exception e) {
                            e.printStackTrace();
                        }

                        // Only if we have both values...
                        if(firstValue != null && secondValue != null) {
                            switch(inputs[1]) {

                                case "RSHIFT": // >>
                                    wire.setSignal(firstValue >> secondValue);
                                    break;

                                case "LSHIFT": // <<
                                    wire.setSignal(firstValue << secondValue);
                                    break;

                                case "AND": // &
                                    wire.setSignal(firstValue & secondValue);
                                    break;

                                case "OR": // |   inclusive
                                    wire.setSignal(firstValue | secondValue);
                                    break;
                            }

                            System.out.println(wire.getIdentifier() + " -- signal processed!");
                        }
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

        //todo: some serious bs incoming
        for(Wire w : wires) {
            if (!w.getIdentifier().equalsIgnoreCase(identifier)) {
                for(String s : w.getExpression().split(" ")) {
                    if(s.equalsIgnoreCase(identifier)) {
                        dependants.add(w);
                        break;
                    }
                }
            }


        }
        return dependants;
    }

    /**
     * Represents a wire in bobby tables' electronics kit
     */
    private class Wire {

        private final String identifier;
        private Integer signal;
        private String expression;

        public Wire(String identifier) {
            this.identifier = identifier;
        }

        public String getIdentifier() {
            return identifier;
        }

        public Integer getSignal() {
            return signal;
        }

        public void setSignal(int signal) {
            this.signal = signal;
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }
    }
}
