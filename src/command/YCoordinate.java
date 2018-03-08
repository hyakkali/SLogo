
package command;

import controller.Controller;

/**
 * Gives the user the turtle's y-coordinate from the center of the screen
 * @author dylanpowers
 *
 */
public class YCoordinate implements Command {

	/**
	 * Empty constructor
	 */
	public YCoordinate() { }

	/**
	 * Gets turtle's y-coordinate
	 * @return turtle's y-coordinate from the center of the screen
	 */
	@Override
	public double execute(Controller controller) {
		return controller.getTurtleYLocation();
	}
}
