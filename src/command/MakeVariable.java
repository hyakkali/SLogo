package command;

import java.util.List;
import controller.Controller;

public class MakeVariable implements Command {
	
    private Variable variable;
    private Constant constant;

    /**
     * Initialize this command with an instance of a variable, which is either 
     * @param variable
     */
    public MakeVariable(Command variable, Command constant) {
    		this.variable = (Variable) variable;
    		this.constant = (Constant) constant;
    }

    @Override
    public double execute(Controller controller) {
        this.variable.setValue(this.constant.execute(controller));
        controller.getMyData().addVariable(this.variable);
        return this.variable.getValue();
    }
}
