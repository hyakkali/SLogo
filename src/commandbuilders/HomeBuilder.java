package commandbuilders;

import backend.Executor;
import command.Command;
import command.Home;
import controller.Controller;

/**
 * Creates a home command (and does not build an actual home)
 * @author dylanpowers
 *
 */
public class HomeBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public HomeBuilder() {
	}

	/**
	 * Build a home command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a home command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Home();
	}

}
