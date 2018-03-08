package commandbuilders;

import backend.Executor;
import command.Backward;
import command.Command;
import controller.Controller;

/**
 * Class to create a backward command
 * @author dylanpowers
 *
 */
public class BackwardBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public BackwardBuilder() {
	}

	/**
	 * Build a backward command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a backward command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Backward(context.getNextCommand().execute(controller));
	}

}
