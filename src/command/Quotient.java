package command;

import controller.Controller;
/**
 * Returns the quotient of two numbers
 * @author dylanpowers
 *
 */
public class Quotient implements Command {

	private Double expr1;
	private Double expr2;
	
	/**
	 * Initialize values of expr1 and expr2
	 */
	public Quotient(Double expr2, Double expr1) {
		// because of the way the parser works, expr2 is fed in first and expr1 is fed in second
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	
	/**
	 * Return the quotient of expr1 and expr2
	 * @return expr1 / expr2
	 */
	@Override
	public double execute(Controller controller) {
		if (this.expr2 == 0) {
			throw new IllegalArgumentException("Cannot divide by zero.");
			// TODO display alert
		}
		return expr1 / expr2;
		
	}
}
