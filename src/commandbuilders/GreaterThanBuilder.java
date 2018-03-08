package commandbuilders;

import backend.Executor;
import command.Command;
import command.GreaterThan;
import controller.Controller;

/**
 * Class to create a GreaterThan object
 * @author dylanpowers
 *
 */
public class GreaterThanBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public GreaterThanBuilder() {
	}

	/**
	 * Build a GreaterThan command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a GreaterThan command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new GreaterThan(context.getNextCommand().execute(controller), context.getNextCommand().execute(controller));
	}

}
