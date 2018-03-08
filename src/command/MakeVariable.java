package command;

import backend.Variable;
import java.util.List;
import controller.Controller;

public class MakeVariable implements Command {

    private double myVariableID;
    private double myVal;

    public MakeVariable(double value, double ID) {
        this.myVariableID = ID;
        this.myVal = value;
    }

    @Override
    public double execute(Controller controller) {
        double retVal = 0.0;
        List<Variable> myVariables = controller.getMyData().getMyVariables();
        for (Variable v : myVariables) {
            if (v.getMyID() == this.myVariableID) {
                v.setValue(this.myVal);
                retVal = this.myVal;
                break;
            }
        }
        System.out.println("retVal" + retVal);
        return retVal;
    }
}
