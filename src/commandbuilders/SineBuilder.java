package commandbuilders;

import backend.Executor;
import command.Command;
import controller.Controller;
import command.Sine;

/**
 * Class to build a Sine command
 * @author dylanpowers
 *
 */
public class SineBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public SineBuilder() {
	}

	/**
	 * Build a Sine command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a Sine command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Sine(context.getNextCommand().execute(controller));
	}

}
