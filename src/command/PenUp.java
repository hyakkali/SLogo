package command;

import main.Controller;
/**
 * When this command is activated, turtle will not leave a trail.
 * @author dylanpowers
 *
 */
public class PenUp implements Command {

	/**
	 * Empty constructor.
	 */
	public PenUp() { }
	
	/**
	 * Picks the pen up so that the turtle does not leave a trail.
	 * @return 0
	 */
	@Override
	public double execute(Controller controller) {
		controller.togglePen(false);
		return 0;
	}
}
