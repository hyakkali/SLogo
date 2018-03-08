package commandbuilders;

import backend.Executor;
import command.Command;
import command.Quotient;
import controller.Controller;

/**
 * Class to create a Quotient command
 * @author dylanpowers
 *
 */
public class QuotientBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public QuotientBuilder() {
	}

	/**
	 * Build a quotient command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a Quotient command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Quotient(context.getNextCommand().execute(controller), context.getNextCommand().execute(controller));
	}

}
