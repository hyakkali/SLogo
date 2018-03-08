package commandbuilders;

import backend.Executor;
import command.Command;
import command.Random;
import controller.Controller;

/**
 * Class to build a Random command (meaning it generates a random number, not that it is just a random command)
 * @author dylanpowers
 *
 */
public class RandomBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public RandomBuilder() {
	}

	/**
	 * Build a Random command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a Random command command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Random(context.getNextCommand().execute(controller));
	}

}
