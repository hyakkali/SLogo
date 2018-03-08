package commandbuilders;

import backend.Executor;
import command.Command;
import command.NotEqual;
import controller.Controller;

/**
 * Class to create a NotEqual command
 * @author dylanpowers
 *
 */
public class NotEqualBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public NotEqualBuilder() {
	}

	/**
	 * Build a NotEqual command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a NotEqual command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new NotEqual(context.getNextCommand().execute(controller), context.getNextCommand().execute(controller));
	}

}
