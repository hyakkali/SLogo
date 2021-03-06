package command;

import controller.Controller;
/**
 * Finds if a number is 0.
 * @author dylanpowers
 *
 */
public class Not implements Command {

	private double test;
	
	/**
	 * Initialize test1 and test2
	 */
	public Not(double test) {
		this.test = test;
	}
	
	/**
	 * @return 1 if test is 0, 0 o.w.
	 */
	@Override
	public double execute(Controller controller) {
		return (this.test == 0) ? 1 : 0;
	}

}
