package commandbuilders;

import backend.Executor;
import command.Command;
import command.IfElse;
import controller.Controller;

/**
 * Class to create an IfElse command
 * @author dylanpowers
 *
 */
public class IfElseBuilder extends CommandBuilder {
	
	/**
	 * Initialize
	 */
	public IfElseBuilder() {}

	/**
	 * Build an if-else command.
	 * @param controller the controller to execute the commands on
	 * @param context the executor which contains the next commands
	 * @return an IfElse command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new IfElse(context.getNextCommand().execute(controller), context.getNextCommand(), context.getNextCommand());
	}

}
