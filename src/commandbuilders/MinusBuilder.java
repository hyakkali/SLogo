package commandbuilders;

import backend.Executor;
import command.Command;
import command.Minus;
import controller.Controller;

/**
 * Class to create a Minus command
 * @author dylanpowers
 *
 */
public class MinusBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public MinusBuilder() {
	}

	/**
	 * Build a Minus command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a Minus command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Minus(context.getNextCommand().execute(controller));
	}

}
