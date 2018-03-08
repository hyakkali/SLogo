package commandbuilders;

import backend.Executor;
import command.Command;
import controller.Controller;
import command.ShowTurtle;

/**
 * Class to create a ShowTurtle command
 * @author dylanpowers
 *
 */
public class ShowTurtleBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public ShowTurtleBuilder() {
	}

	/**
	 * Build a ShowTurtle command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a ShowTurtle command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new ShowTurtle();
	}

}
