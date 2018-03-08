package commandbuilders;

import backend.Executor;
import command.Command;
import controller.Controller;
import command.Tangent;

/**
 * Class to build a Tangent 
 * @author dylanpowers
 *
 */
public class TangentBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public TangentBuilder() {
	}

	/**
	 * Build a Tangent command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a Tangent command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Tangent(context.getNextCommand().execute(controller));
	}

}
