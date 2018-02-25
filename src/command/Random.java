package command;
/**
 * Returns a random number strictly less than a given maximum
 * @author dylanpowers
 *
 */
public class Random implements Command {

	private Double max;
	
	/**
	 * Initializes max
	 */
	public Random(Double max) {
		this.max = max;
	}
	
	/**
	 * Returns a random number strictly less than max
	 */
	public double execute(Controller controller) {
		if (this.max == 0) return 0;
		if (this.max < 0) {
			throw new IllegalArgumentException("Maximum must be greater than or equal to 0.");
			// TODO display alert
		}
		return Math.random() * this.max;
	}
}
