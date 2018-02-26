package command;

import main.Controller;
/**
 * Returns Pi (3.1415926535897932384626...)
 * @author dylanpowers
 *
 */
public class Pi implements Command {

	/**
	 * Empty constructor
	 */
	public Pi() { }
	
	/**
	 * Gives Pi to the user. Not the fun, tasty kind - the boring, math kind.
	 * @return Pi
	 */
	@Override
	public double execute(Controller controller) {
		return Math.PI;
	}
}
