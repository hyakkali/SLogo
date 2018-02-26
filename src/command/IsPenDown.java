package command;

import main.Controller;

/**
 * Tells the user if the pen is down or not
 * @author dylanpowers
 *
 */
public class IsPenDown implements Command {

	/**
	 * Empty constructor
	 */
	public IsPenDown() { }
	
	/**
	 * Gets the status of the pen
	 * @return 1 if turtle's pen is down, 0 o.w.
	 */
	@Override
	public double execute(Controller controller) {
		return controller.getIsPen() ? 1 : 0;
	}
}
