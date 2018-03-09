package commandbuilders;

import backend.Executor;
import command.Command;
import command.DoTimes;
import controller.Controller;

/**
 * Class to build a DoTimes command, which is like a loop that replaces the value of a variable periodically
 * @author dylanpowers
 *
 */
public class DoTimesBuilder extends CommandBuilder {
	
	/**
	 * Initialize
	 */
	public DoTimesBuilder() {}
	
	/**
	 * Build a DoTimes command.
	 * @param controller the controller to execute the commands on
	 * @param context the executor which contains the other commands
	 * @return a DoTimes command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new DoTimes(context.getNextCommand(), context.getNextCommand());
	}

}
