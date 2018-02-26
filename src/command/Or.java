package command;

import main.Controller;

/**
 * Finds if either of two numbers is non-zero
 * @author dylanpowers
 *
 */
public class Or implements Command {

	private Double test1;
	private Double test2;
	
	/**
	 * Initialize test1 and test2
	 */
	public Or(Double test1, Double test2) {
		this.test1 = test1;
		this.test2 = test2;
	}
	
	/**
	 * @return 1 if test1 or test2 are non-zero, 0 o.w.
	 */
	public double execute(Controller controller) {
		return (this.test1 != 0 || this.test2 != 0) ? 1 : 0;
	}

}
