package command;

import controller.Controller;
/**
 * Find if a given number is less than another given number.
 * @author dylanpowers
 *
 */
public class LessThan implements Command {

	private double expr1;
	private double expr2;
	
	/**
	 * Initialize expr1 and expr2
	 */
	public LessThan(double expr1, double expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	
	/**
	 * @return 1 if expr1 is strictly less than expr2, 0 otherwise
	 */
	@Override
	public double execute(Controller controller) {
		return this.expr1 < this.expr2 ? 1 : 0;
	}
}
