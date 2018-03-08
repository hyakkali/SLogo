package commandbuilders;

import backend.Executor;
import command.Command;
import command.MakeVariable;
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
		return new MakeVariable(context.getNextCommand().execute(controller), context.getNextCommand().execute(controller));
	}
}
