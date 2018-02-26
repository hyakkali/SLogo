package command;

import main.Controller;
/**
 * Interface that all commands (ex. Forward, Backward, Change picture, etc.) must implement.
 * @author dylanpowers
 *
 */
public interface Command {

	/**
	 * Execute this command on the given turtle
	 * @param controller the controller for the command to be executed on.
	 */
	public double execute(Controller controller);
}
