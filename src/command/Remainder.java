package command;

import controller.Controller;
/**
 * Returns the remainder of two numbers
 * @author dylanpowers
 *
 */
public class Remainder implements Command {

	private Double expr1;
	private Double expr2;
	
	/**
	 * Initializes expr1 and expr2
	 */
	public Remainder(Double expr1, Double expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	
	/**
	 * Returns the remainder of expr1 and expr2
	 * @return expr1 % expr2
	 */
	@Override
	public double execute(Controller controller) {
		if (this.expr2 == 0) {
			throw new IllegalArgumentException("Cannot divide by 0.");
			// TODO display alert
		}
		return expr1 % expr2;
	}
}
