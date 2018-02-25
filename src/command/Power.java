package command;
/**
 * Raises a given base to a given exponent.
 * @author dylanpowers
 *
 */
public class Power implements Command {

	private Double base;
	private Double exponent;
	
	/**
	 * Initializes base and exponent
	 */
	public Power(Double base, Double exponent) {
		this.base = base;
		this.exponent = exponent;
	}
	
	/**
	 * Calculates base^exponent
	 * @return base^exponent
	 */
	public double execute(Controller controller) {
		return Math.pow(this.base, this.exponent);
	}
}
