package commandbuilders;

import backend.Executor;
import command.Command;
import command.Repeat;
import controller.Controller;

/**
 * Class to create a Repeat command
 * @author dylanpowers
 *
 */
public class RepeatBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public RepeatBuilder() {}

	/**
	 * Build a repeat command, which repeats a certain command 'expr' number of times
	 * @param controller the controller to execute these commands on
	 * @param context the executor which contains the other commands (hopefully a list)
	 * @return a Repeat command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Repeat(context.getNextCommand().execute(controller), context.getNextCommand());
	}

}
