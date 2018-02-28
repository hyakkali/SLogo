package command;

import controller.Controller;
/**
 * Finds if two numbers are not equal to each other
 * @author dylanpowers
 *
 */
public class NotEqual implements Command {

	private Double expr1;
	private Double expr2;
	
	/**
	 * Initialize expr1 and expr2
	 */
	public NotEqual(Double expr1, Double expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	
	/**
	 * @return 1 if expr1 is not equal to expr2, 0 o.w.
	 */
	@Override
	public double execute(Controller controller) {
		return this.expr1 != this.expr2 ? 1 : 0;
	}

}
