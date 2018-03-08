package commandbuilders;

import backend.Executor;
import command.Command;
import command.Right;
import controller.Controller;

/**
 * Class to build a right command
 * @author dylanpowers
 *
 */
public class RightBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public RightBuilder() {
		
	}

	/**
	 * Build a right command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a right command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Right(context.getNextCommand().execute(controller));
	}

}
