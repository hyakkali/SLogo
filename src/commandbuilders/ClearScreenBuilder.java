package commandbuilders;

import backend.Executor;
import command.ClearScreen;
import command.Command;
import controller.Controller;

/**
 * Class to build a ClearScreen command
 * @author dylanpowers
 *
 */
public class ClearScreenBuilder extends CommandBuilder {

	/**
	 * Empty contsructor
	 */
	public ClearScreenBuilder() {
	}

	/**
	 * Build a ClearScreen command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a ClearScreen command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new ClearScreen();
	}

}
