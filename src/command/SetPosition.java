package command;

import main.Controller;
/**
 * Move the turtle to a specified position on the screen.
 * @author dylanpowers
 *
 */
public class SetPosition implements Command {

	private Double x;
	private Double y;
	
	/**
	 * 
	 * @param x x coordinate to move to
	 * @param y y coordinate to move to
	 */
	public SetPosition(Double x, Double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Move the turtle to the location (x, y)
	 * @return distance that turtle moved
	 */
	@Override
	public double execute(Controller controller) {
		// calculate distance using distance formula 
		double turtleX = controller.getTurtleXLocation();
		double turtleY = controller.getTurtleYLocation();
		double distance = Math.sqrt(Math.pow(this.x - turtleX, 2) + Math.pow(this.y - turtleY, 2));
		controller.setTurtleXLocation(this.x);
		controller.setTurtleYLocation(this.y);
		return distance;
	}
}
