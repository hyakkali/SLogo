package command;

import controller.Controller;
/**
 * Finds the sine of a given degree value
 * @author dylanpowers
 *
 */
public class Sine implements Command {

	public Double degrees;
	
	/**
	 * Initializes degrees
	 */
	public Sine(Double degrees) {
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
