
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
		return 0.0;
	}
}
		// get current x and y coordinates so that we can calculate distance

