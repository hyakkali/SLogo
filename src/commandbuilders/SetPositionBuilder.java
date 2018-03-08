package commandbuilders;

import backend.Executor;
import command.Command;
import controller.Controller;
import command.SetPosition;

/**
 * Class to build a SetPosition command
 * @author dylanpowers
 *
 */
public class SetPositionBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public SetPositionBuilder() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Build a SetPosition command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a SetPosition command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new SetPosition(context.getNextCommand().execute(controller), context.getNextCommand().execute(controller));
	}

}
