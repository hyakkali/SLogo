package commandbuilders;

import backend.Executor;
import command.Command;
import controller.Controller;
import command.IsShowing;

/**
 * Creates an IsShowing command
 * @author dylanpowers
 *
 */
public class IsShowingBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public IsShowingBuilder() {
	}

	/**
	 * Build an IsShowing command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return an IsShowing command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new IsShowing();
	}

}
