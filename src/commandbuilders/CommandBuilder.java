package commandbuilders;

import backend.Executor;
import command.Command;
import controller.Controller;

/**
 * Superclass for building commands.
 * @author dylanpowers
 *
 */
public abstract class CommandBuilder {
	
	/**
	 * Build and execute a given command
	 * @param controller the controller for which the command should act on
	 * @return the result of the most recent command executed
	 */
	public abstract Command build(Controller controller, Executor context);
	
}
