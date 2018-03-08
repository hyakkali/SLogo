package command;

import controller.Controller;
/**
 * Gets and returns the sum of expr1 and expr2
 * @author dylanpowers
 *
 */
public class Sum implements Command {

	private double expr1;
	private double expr2;
	
	/**
	 * Initializes values of expr1 and expr2
	 */
	public Sum(double expr1, double expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	
	/**
	 * Adds the two expressions together, returns it
	 * @return expr1 + expr2
	 */
	@Override
	public double execute(Controller controller) {
		return this.expr1 + this.expr2;
	}
}
