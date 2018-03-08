package commandbuilders;

import backend.Executor;
import command.Command;
import command.Left;
import controller.Controller;

/**
 * Class to create a left command
 * @author dylanpowers
 *
 */
public class LeftBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public LeftBuilder() {
	}
	
	/**
	 * Build a left command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a left command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Left(context.getNextCommand().execute(controller));
	}

}
