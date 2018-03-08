package command;

import controller.Controller;
/**
 * Returns the difference between two expressions
 * @author dylanpowers
 *
 */
public class Difference implements Command {

	private double expr1;
	private double expr2;
	
	/**
	 * Initialize with values of expr1 and expr2
	 */
	public Difference(double expr1, double expr2) {
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
