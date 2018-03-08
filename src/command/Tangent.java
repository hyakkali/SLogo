package command;

import controller.Controller;
/**
 * Finds the tangent of a given angle
 * @author dylanpowers
 *
 */
public class Tangent implements Command {

	private double degrees;
	
	/**
	 * Initializes degrees
	 */
	public Tangent(double degrees) {
		this.degrees = degrees;
	}
	
	/**
	 * Finds the tangent of an angle.
	 * @return tan(degrees)
	 */
	@Override
	public double execute(Controller controller) {
		// Math.tan() takes radians as a parameter, so change degrees to radians
		return Math.tan(Math.toRadians(this.degrees));
	}
}
