package command;

import controller.Controller;
/**
 * Finds if either of two numbers is non-zero
 * @author dylanpowers
 *
 */
public class Or implements Command {

	private double test1;
	private double test2;
	
	/**
	 * Initialize test1 and test2
	 */
	public Or(double test1, double test2) {
		this.test1 = test1;
		this.test2 = test2;
	}
	
	/**
	 * @return 1 if test1 or test2 are non-zero, 0 o.w.
	 */
	@Override
	public double execute(Controller controller) {
		return (this.test1 != 0 || this.test2 != 0) ? 1 : 0;
	}

}
