package commandbuilders;

import backend.Executor;
import command.Command;
import command.PenDown;
import controller.Controller;

/**
 * Class to create a PenDown command
 * @author dylanpowers
 *
 */
public class PenDownBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public PenDownBuilder() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Build a PenDown command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a PenDown command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new PenDown();
	}
}
