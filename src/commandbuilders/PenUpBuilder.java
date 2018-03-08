package commandbuilders;

import backend.Executor;
import command.Command;
import command.PenUp;
import controller.Controller;

/**
 * Class to create a PenUp command
 * @author dylanpowers
 *
 */
public class PenUpBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public PenUpBuilder() {
	}

	/**
	 * Build a PenUp command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a PenUp command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new PenUp();
	}

}
