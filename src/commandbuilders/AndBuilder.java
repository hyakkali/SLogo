package commandbuilders;

import backend.Executor;
import command.And;
import command.Command;
import controller.Controller;

/**
 * Build an add command
 * @author dylanpowers
 *
 */
public class AndBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public AndBuilder() {
	}

	/**
	 * Build an and command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return an and command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new And(context.getNextCommand().execute(controller), context.getNextCommand().execute(controller));
	}

}
