package commandbuilders;

import backend.Executor;
import command.Command;
import controller.Controller;
import command.Sum;

/**
 * Class to build a Sum command
 * @author dylanpowers
 *
 */
public class SumBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public SumBuilder() {
	}

	/**
	 * Build a Sum command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a Sum command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Sum(context.getNextCommand().execute(controller), context.getNextCommand().execute(controller));
	}

}
