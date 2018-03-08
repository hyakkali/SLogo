package commandbuilders;

import backend.Executor;
import command.Command;
import command.LessThan;
import controller.Controller;

/**
 * Create a LessThan command
 * @author dylanpowers
 *
 */
public class LessThanBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public LessThanBuilder() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Build a LessThan command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a LessThan command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new LessThan(context.getNextCommand().execute(controller), context.getNextCommand().execute(controller));
	}

}
