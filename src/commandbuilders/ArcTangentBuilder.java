package commandbuilders;

import backend.Executor;
import command.Command;
import controller.Controller;
import command.ArcTangent;

/**
 * Class to make an ArcTangent command
 * @author dylanpowers
 *
 */
public class ArcTangentBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public ArcTangentBuilder() {
	}

	/**
	 * Build an arctan command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return an arctan command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new ArcTangent(context.getNextCommand().execute(controller));
	}

}
