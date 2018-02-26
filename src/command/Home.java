package command;

import main.Controller;
/**
 * Moves turtle to the point (0, 0)
 * @author dylanpowers
 *
 */
public class Home implements Command {

	/**
	 * Empty constructor
	 */
	public Home() { }
	
	/**
	 * Moves the turtle to the center of the screen (0, 0)
	 * @return distance that the turtle moved
	 */
	public double execute(Controller controller) {
		// get current x and y coordinates so that we can calculate distance
		double turtleX = controller.getTurtleXLocation();
		double turtleY = controller.getTurtleYLocation();
		double distance = Math.sqrt(Math.pow(turtleX, 2) + Math.pow(turtleY, 2));
		controller.setTurtleXLocation(0);
		controller.setTurtleYLocation(0);
		return distance;
	}
}
