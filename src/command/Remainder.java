package command;

import controller.Controller;
/**
 * Returns the remainder of two numbers
 * @author dylanpowers
 *
 */
public class Remainder implements Command {

	private double expr1;
	private double expr2;
	
	/**
	 * Initializes expr1 and expr2
	 */
	public Remainder(double expr1, double expr2) {
		// because of the way the parser works, expr2 is fed in first and expr1 is fed in second
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
