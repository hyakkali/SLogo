package commandbuilders;

import backend.Executor;
import command.Command;
import command.For;
import controller.Controller;

/**
 * Class to create a For command.
 * @author dylanpowers
 *
 */
public class ForBuilder extends CommandBuilder {

	/**
	 * Initialize
	 */
	public ForBuilder() {}

	/**
	 * Build a For command that will execute commands in a loop.
	 * @param controller the controller to execute commands on
	 * @param context the executor which contains the next commands
	 * @return a For command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new For(context.getNextCommand(), context.getNextCommand());
	}

}
