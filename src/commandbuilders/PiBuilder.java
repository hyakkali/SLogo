package commandbuilders;

import backend.Executor;
import command.Command;
import command.Pi;
import controller.Controller;

/**
 * Class to create a Pi command, which literally just returns the number pi.
 * @author dylanpowers
 *
 */
public class PiBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public PiBuilder() {
	}
	
	/**
	 * Build a new pi command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a pi command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Pi();
	}

}
