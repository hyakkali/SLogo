package command;

import controller.Controller;

/**
 * Tells the user if the turtle is currently showing or not
 * @author dylanpowers
 *
 */
public class IsShowing implements Command {

	/**
	 * Empty constructor
	 */
	public IsShowing() { }
	
	/**
	 * Gets the status of the turtle's visibility
	 * @return 1 if turtle is showing, 0 o.w.
	 */
	@Override
	public double execute(Controller controller) {
		return controller.getIsTurtle() ? 1 : 0;
	}
}
