package command;

import controller.Controller;
/**
 * Returns the product of two numbers
 * @author dylanpowers
 *
 */
public class Product implements Command {

	private double expr1;
	private double expr2;
	
	/**
	 * Initializes values of expr1 and expr2
	 */
	public Product(double expr1, double expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	
	/**
	 * Returns the product of expr1 and expr2
	 * @return expr1 * expr2
	 */
	@Override
	public double execute(Controller controller) {
		return expr1 * expr2;
	}
}
