package command;

import main.Controller;
<<<<<<< HEAD

=======
>>>>>>> 95dc8841f134d5094a8dc6217737581d77b73b0f
/**
 * Returns the product of two numbers
 * @author dylanpowers
 *
 */
public class Product implements Command {

	private Double expr1;
	private Double expr2;
	
	/**
	 * Initializes values of expr1 and expr2
	 */
	public Product(Double expr1, Double expr2) {
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
