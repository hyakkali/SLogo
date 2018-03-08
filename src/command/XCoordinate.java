
package command;

import controller.Controller;

/**
 * Gives the user the turtle's x-coordinate from the center of the screen
 * @author dylanpowers
 *
 */
public class XCoordinate implements Command {

	/**
	 * Empty constructor
	 */
	public XCoordinate() { }

	/**
	 * Get turtle's x coordinate
	 * @return turtle's x-coordinate from center of the screen
	 */
	@Override
	public double execute(Controller controller) {
		return controller.getTurtleXLocation();
	}
}
