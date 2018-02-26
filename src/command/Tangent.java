package command;

import main.Controller;

/**
 * Finds the tangent of a given angle
 * @author dylanpowers
 *
 */
public class Tangent implements Command {

	private Double degrees;
	
	/**
	 * Initializes degrees
	 */
	public Tangent(Double degrees) {
		this.degrees = degrees;
	}
	
	/**
	 * Finds the tangent of an angle.
	 * @return the tangent of an angle
	 */
	public double execute(Controller controller) {
		// Math.tan() takes radians as a parameter, so change degrees to radians
		return Math.tan(Math.toRadians(this.degrees));
	}
}
