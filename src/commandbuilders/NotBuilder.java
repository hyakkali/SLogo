package commandbuilders;

import backend.Executor;
import command.Command;
import command.Not;
import controller.Controller;

/**
 * Class to create a Not command
 * @author dylanpowers
 *
 */
public class NotBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public NotBuilder() {
	}

	/**
	 * Build a Not command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a Not command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Not(context.getNextCommand().execute(controller));
	}

}
