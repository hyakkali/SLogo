package command;

import controller.Controller;
/**
 * Finds the sine of a given degree value
 * @author dylanpowers
 *
 */
public class Sine implements Command {

	public double degrees;
	
	/**
	 * Initializes degrees
	 */
	public Sine(double degrees) {
		this.degrees = degrees;
	}
	
	/**
	 * Returns the sine of degrees
	 * @return sin(degrees)
	 */
	@Override
	public double execute(Controller controller) {
		// Math.sin() takes radians as a parameter, so change degrees to radians
		return Math.sin(Math.toRadians(this.degrees));
	}

}
