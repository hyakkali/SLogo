package commandbuilders;

import backend.Executor;
import command.Command;
import command.If;
import controller.Controller;

/**
 * Class to build an If command
 * @author dylanpowers
 *
 */
public class IfBuilder extends CommandBuilder {

	/**
	 * Initialize
	 */
	public IfBuilder() {}

	/**
	 * Build an If command, which evaluates the commands inside of it iff its evaluator is 0
	 * @param controller the controller for the command to be executed on
	 * @param context the executor which holds the other commands
	 * @return an If command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new If(context.getNextCommand().execute(controller), context.getNextCommand());
	}

}
