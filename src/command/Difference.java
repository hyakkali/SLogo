package command;

import controller.Controller;
/**
 * Returns the difference between two expressions
 * @author dylanpowers
 *
 */
public class Difference implements Command {

	private Double expr1;
	private Double expr2;
	
	/**
	 * Initialize with values of expr1 and expr2
	 */
	public Difference(Double expr2, Double expr1) {
		// because of the way the parser works, expr2 is fed in first and expr1 is fed in second
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	
	/**
	 * Return the difference between these two expressions
	 * @return value of expr1 - expr2
	 */
	@Override
	public double execute(Controller controller) {
		return this.expr1 - this.expr2;
	}
}
