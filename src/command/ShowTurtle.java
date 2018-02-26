package command;

import main.Controller;

/**
 * When this command is run, the turtle is made visible.
 * @author dylanpowers
 *
 */
public class ShowTurtle implements Command {

	/**
	 * Empty constructor
	 */
	public ShowTurtle() { }
	
	/**
	 * Makes the turtle visible.
	 * @return 1
	 */
	@Override
	public double execute(Controller controller) {
		controller.toggleTurtleDisplay(true);
		return 1;
	}
}
