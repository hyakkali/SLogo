package commandbuilders;

import backend.Executor;
import command.Command;
import command.Equal;
import controller.Controller;

/**
 * Class to create an Equals command, which tests the equality of two expressions
 * @author dylanpowers
 *
 */
public class EqualBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public EqualBuilder() {
	}

	/**
	 * Build an equals command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return an equals command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Equal(context.getNextCommand().execute(controller), context.getNextCommand().execute(controller));
	}

}
