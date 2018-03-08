package command;

import controller.Controller;
/**
 * Returns the negative of a number
 * @author dylanpowers
 *
 */
public class Minus implements Command {

	private double expr1;
	
	/**
	 * Initializes expr1
	 */
	public Minus(double expr1) {
		this.expr1 = expr1;
	}
	
	/**
	 * Returns the negative of expr1
	 * @return -expr1
	 */
	@Override
	public double execute(Controller controller) {
		return -expr1;
	}
}
