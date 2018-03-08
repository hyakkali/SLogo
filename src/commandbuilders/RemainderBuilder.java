package commandbuilders;

import backend.Executor;
import command.Command;
import command.Remainder;
import controller.Controller;

/**
 * Class to build a Remainder command
 * @author dylanpowers
 *
 */
public class RemainderBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public RemainderBuilder() {
	}

	/**
	 * Build a Remainder command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a Remainder command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Remainder(context.getNextCommand().execute(controller), context.getNextCommand().execute(controller));
	}

}
