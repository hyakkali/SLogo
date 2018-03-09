package command;

import controller.Controller;

public class Variable implements Command {
    private String myName;
    private double myValue;
    private double myID;
    private final String BAD_VAR_MESSAGE = "Variables must start with a colon.";

    public Variable(String name) {
    		// variables must start with colons
    		if (!name.startsWith(":")) {
    			throw new CommandException(BAD_VAR_MESSAGE);
    		}
        myName = name;
        myID = (double) name.toCharArray().hashCode();
    }

    public String getName() {
        return this.myName;
    }

    public double getValue() {
        return this.myValue;
    }

    public void setValue(double val) {
        this.myValue = val;
    }

    public double getMyID() {
        return myID;
    }
    
    /**
     * Execute this command, which just simply returns the value
     * @return the value of this variable
     */
    public double execute(Controller controller) {
    		return this.myValue;
    }
}
