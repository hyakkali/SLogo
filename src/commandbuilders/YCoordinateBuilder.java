package commandbuilders;

import backend.Executor;
import command.Command;
import controller.Controller;
import command.YCoordinate;

/**
 * Class to build a YCoordinate command
 * @author dylanpowers
 *
 */
public class YCoordinateBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public YCoordinateBuilder() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Build a YCoordinate command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a YCoordinate command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new YCoordinate();
	}
}
