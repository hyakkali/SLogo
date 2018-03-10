package commandbuilders;

import backend.Executor;
import command.Command;
import command.SetHeading;
import controller.Controller;

/**
 * Class to build a SetHeading command
 * @author dylanpowers
 *
 */
public class SetHeadingBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public SetHeadingBuilder() {}

	/**
	 * Build a SetHeading command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a SetHeading command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new SetHeading(context.getNextCommand().execute(controller));
	}

}
