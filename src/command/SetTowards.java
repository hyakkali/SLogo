package command;

import controller.Controller;

/**
 * Sets the turtle to face a certain place on the screen.
 * @author dylanpowers
 *
 */
public class SetTowards implements Command {

	private double x;
	private double y;
	/**
	 * 
	 * @param x x coordinate that turtle should face
	 * @param y y coordinate that turtle should face
	 */
	public SetTowards(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Makes the turtle face the location (x, y)
	 * @return number of degrees the turtle turned
	 */
	@Override
	public double execute(Controller controller) {
		// need to keep track of the previous heading, and then subtract from the new one
		double prevHeading = controller.getTurtleHeading();
		controller.setTurtleTowards(this.x, this.y);
		// heading is now updated, so we call it again
		return Math.abs(controller.getTurtleHeading() - prevHeading);
	}

}
