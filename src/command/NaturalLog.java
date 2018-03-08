package command;

import controller.Controller;
/**
 * Finds the natural logarithm of a number.
 * @author dylanpowers
 *
 */
public class NaturalLog implements Command {

	private double expr;
	
	/**
	 * Initializes expr
	 */
	public NaturalLog(double expr) {
		this.expr = expr;
	}
	
	/**
	 * Finds the natural logarithm of a given number.
	 * @return ln(expr)
	 */
	@Override
	public double execute(Controller controller) {
		return Math.log(this.expr);
	}
}
