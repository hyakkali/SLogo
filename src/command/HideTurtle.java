package command;

import controller.Controller;

/**
 * When this command is run, the turtle is hidden
 * @author dylanpowers
 *
 */
public class HideTurtle implements Command {

	/**
	 * Empty constructor
	 */
	public HideTurtle() { }
	
	/**
	 * Makes the turtle invisible.
	 * @return 0
	 */
	@Override
	public double execute(Controller controller) {
		controller.toggleTurtleDisplay(false);
		return 0;
	}
}
