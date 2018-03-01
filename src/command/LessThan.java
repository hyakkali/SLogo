package command;

import controller.Controller;
/**
 * Find if a given number is less than another given number.
 * @author dylanpowers
 *
 */
public class LessThan implements Command {

	private Double expr1;
	private Double expr2;
	
	/**
	 * Initialize expr1 and expr2
	 */
	public LessThan(Double expr2, Double expr1) {
		// because of the way the parser works, expr2 is fed in first and expr1 is fed in second
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
