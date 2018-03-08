package commandbuilders;

import backend.Executor;
import command.Command;
import command.HideTurtle;
import controller.Controller;

/**
 * Class to create a HideTurtle command
 * @author dylanpowers
 *
 */
public class HideTurtleBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public HideTurtleBuilder() {
	}

	/**
	 * Build a HideTurtle command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a HideTurtle
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new HideTurtle();
	}

}
