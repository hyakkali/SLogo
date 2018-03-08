package commandbuilders;

import backend.Executor;
import command.Command;
import command.Difference;
import controller.Controller;

/**
 * Class to build a Difference command which subtracts two numbers
 * @author dylanpowers
 *
 */
public class DifferenceBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public DifferenceBuilder() {
	}

	/**
	 * Build a difference command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a difference command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Difference(context.getNextCommand().execute(controller), context.getNextCommand().execute(controller));
	}

}
