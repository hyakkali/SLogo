package commandbuilders;

import backend.Executor;
import command.Command;
import command.NaturalLog;
import controller.Controller;

/**
 * Create a NaturalLog command
 * @author dylanpowers
 *
 */
public class NaturalLogBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public NaturalLogBuilder() {
	}

	/**
	 * Build a NaturaLog command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a NaturalLog command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new NaturalLog(context.getNextCommand().execute(controller));
	}
}
