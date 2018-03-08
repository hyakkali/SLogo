package command;

import controller.Controller;

public class Variable implements Command {
    private String myName;
    private double myValue;
    private double myID;

    public Variable(String name) {
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
        return this.myID;
    }
    
    /**
     * Execute this command, which just simply returns the value
     * @return the value of this variable
     */
    public double execute(Controller controller) {
    		return this.myValue;
    }
}
