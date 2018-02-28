package backend;

public class Variable {
    private String myName;
    private double myValue;

    public Variable(String name, double value) {
        myName = name;
        myValue = value;
    }

    public String getName() {
        return myName;
    }

    public double getValue() {
        return myValue;
    }
}
