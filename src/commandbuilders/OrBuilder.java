package commandbuilders;

import backend.Executor;
import command.Command;
import command.Or;
import controller.Controller;

/**
 * Class to build an Or command
 * @author dylanpowers
 *
 */
public class OrBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public OrBuilder() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Build an Or command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return an Or command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Or(context.getNextCommand().execute(controller), context.getNextCommand().execute(controller));
	}

}
