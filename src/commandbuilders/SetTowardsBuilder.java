package commandbuilders;

import backend.Executor;
import command.Command;
import controller.Controller;
import command.SetTowards;

/**
 * Class to build a SetTowards command
 * @author dylanpowers
 *
 */
public class SetTowardsBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public SetTowardsBuilder() {}

	/**
	 * Build a SetTowards command
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a SetTowards command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new SetTowards(context.getNextCommand().execute(controller), context.getNextCommand().execute(controller));
	}

}
