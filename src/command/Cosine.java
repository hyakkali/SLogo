package command;

import main.Controller;

/**
 * Returns the cosine of a given number
 * @author dylanpowers
 *
 */
public class Cosine implements Command {

	private Double degrees;
	
	/**
	 * Initializes degrees
	 */
	public Cosine(Double degrees) {
		this.degrees = degrees;
	}
	
	/**
	 * Returns the cosine of a given angle
	 */
	public double execute(Controller controller) {
		// Math.cos() takes radians as a parameter, so change degrees to radians
		return Math.cos(Math.toRadians(this.degrees));
	}
}
