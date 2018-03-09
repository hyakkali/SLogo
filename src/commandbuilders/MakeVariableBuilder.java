package commandbuilders;

import backend.Executor;
import command.Command;
import command.Constant;
import command.MakeVariable;
import command.Variable;
import controller.Controller;

/**
 * Class to create a command that makes and saves variables
 */
public class MakeVariableBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public MakeVariableBuilder() {
	}

	/**
	 * Build a command that makes and saves variables
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a MakeVariable command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		// get the variable and constant
		Variable v = (Variable) context.getNextCommand();
		Constant c = (Constant) context.getNextCommand();
		return new MakeVariable(v, c);
	}
}
