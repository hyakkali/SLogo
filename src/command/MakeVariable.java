package command;

import backend.Variable;
import java.util.List;
import controller.Controller;

public class MakeVariable implements Command {

    private Double myVariableID;
    private Double myVal;

    public MakeVariable(double myVarID, double value) {
        this.myVariableID = myVarID;
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
        return retVal;
    }
}
