package command;

import controller.Controller;
/**
 * Returns the cosine of a given number
 * @author dylanpowers
 *
 */
public class Cosine implements Command {

	private double degrees;
	
	/**
	 * Initializes degrees
	 */
	public Cosine(double degrees) {
		this.degrees = degrees;
	}
	
	/**
	 * Returns the cosine of a given angle
	 * @return cosine of angle
	 */
	@Override
	public double execute(Controller controller) {
		// Math.cos() takes radians as a parameter, so change degrees to radians
		return Math.cos(Math.toRadians(this.degrees));
	}

}
