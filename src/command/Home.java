
package command;

import controller.Controller;

/**
 * Moves turtle to the point (0, 0)
 * @author dylanpowers
 *
 */
public class Home implements Command {

	/**
	 * Empty constructor
	 */
	public Home() {
	}

	/**
	 * Moves the turtle to the center of the screen (0, 0)
	 *
	 * @return distance that the turtle moved
	 */
	@Override
	public double execute(Controller controller) {
		double xpos = controller.getTurtleXLocation();
		double ypos = controller.getTurtleYLocation();
		controller.resetTurtlePosition();
		// calculate and return distance between old position and new position
		return Math.sqrt(Math.pow(xpos - controller.getTurtleXLocation(),  2) + Math.pow(ypos - controller.getTurtleYLocation(), 2));
	}
}

