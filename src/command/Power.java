package command;

import controller.Controller;
/**
 * Raises a given base to a given exponent.
 * @author dylanpowers
 *
 */
public class Power implements Command {

	private double base;
	private double exponent;
	
	/**
	 * Initializes base and exponent
	 */
	public Power(double base, double exponent) {
		this.base = base;
		this.exponent = exponent;
	}
	
	/**
	 * Calculates base^exponent
	 * @return base^exponent
	 */
	@Override
	public double execute(Controller controller) {
		return Math.pow(this.base, this.exponent);
	}
}
