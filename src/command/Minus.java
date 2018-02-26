package command;

import main.Controller;

/**
 * Returns the negative of a number
 * @author dylanpowers
 *
 */
public class Minus implements Command {

	private Double expr1;
	
	/**
	 * Initializes expr1
	 */
	public Minus(Double expr1) {
		this.expr1 = expr1;
	}
	
	/**
	 * Returns the negative of expr1
	 */
	public double execute(Controller controller) {
		return -expr1;
	}
}
