package command;

import main.Controller;

/**
 * Gets and returns the sum of expr1 and expr2
 * @author dylanpowers
 *
 */
public class Sum implements Command {

	private Double expr1;
	private Double expr2;
	
	/**
	 * Initializes values of expr1 and expr2
	 */
	public Sum(Double expr1, Double expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	
	/**
	 * Adds the two expressions together, returns it
	 */
	public double execute(Controller controller) {
		return this.expr1 + this.expr2;
	}
}
