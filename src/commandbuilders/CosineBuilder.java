package commandbuilders;

import backend.Executor;
import command.Command;
import command.Cosine;
import controller.Controller;

/**
 * Class to create a Cosine command
 * @author dylanpowers
 *
 */
public class CosineBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public CosineBuilder() {
	}

	/**
	 * Build a Cosine command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a cosine command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Cosine(context.getNextCommand().execute(controller));
	}

}
