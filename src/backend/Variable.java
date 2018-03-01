package backend;

public class Variable {
    private String myName;
    private double myValue;
    private double myID;

    public Variable(String name, double value) {
        myName = name;
        myValue = value;
        myID = (double) name.toCharArray().hashCode();
    }

    public String getName() {
        return myName;
    }

    public double getValue() {
        return myValue;
    }

    public void setValue(double val) {
        this.myValue = val;
    }

    public double getMyID() {
        return myID;
    }
}
