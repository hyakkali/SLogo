package command;

import controller.Controller;
/**
 * Finds if two numbers are equal to each other
 * @author dylanpowers
 *
 */
public class Equal implements Command {

	private double expr1;
	private double expr2;
	
	/**
	 * Initialize expr1 and expr2
	 */
	public Equal(double expr1, double expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	
	/**
	 * @return 1 if expr1 is equal to expr2, 0 o.w.
	 */
	@Override
	public double execute(Controller controller) {
		return this.expr1 == this.expr2 ? 1 : 0;
	}


}
