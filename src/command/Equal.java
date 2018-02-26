package command;

import main.Controller;

/**
 * Finds if two numbers are equal to each other
 * @author dylanpowers
 *
 */
public class Equal implements Command {

	private Double expr1;
	private Double expr2;
	
	/**
	 * Initialize expr1 and expr2
	 */
	public Equal(Double expr1, Double expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	
	/**
	 * @return 1 if expr1 is equal to expr2, 0 o.w.
	 */
	public double execute(Controller controller) {
		return this.expr1 == this.expr2 ? 1 : 0;
	}

}
