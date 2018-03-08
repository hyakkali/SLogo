package commandbuilders;

import backend.Executor;
import command.Command;
import command.Power;
import controller.Controller;

/**
 * Class to build a Power command. Also, the name of this class is badass.
 * @author dylanpowers
 *
 */
public class PowerBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public PowerBuilder() {
	}

	/**
	 * Build a Power command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a Power command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Power(context.getNextCommand().execute(controller), context.getNextCommand().execute(controller));
	}
}