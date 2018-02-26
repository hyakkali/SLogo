package command;

import main.Controller;

/**
 * Finds the natural logarithm of a number.
 * @author dylanpowers
 *
 */
public class NaturalLog implements Command {

	private Double expr;
	
	/**
	 * Initializes expr
	 */
	public NaturalLog(Double expr) {
		this.expr = expr;
	}
	
	/**
	 * Finds the natural logarithm of a given number.
	 * @return ln(expr)
	 */
	public double execute(Controller controller) {
		return Math.log(this.expr);
	}
}
