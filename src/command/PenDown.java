package command;

import main.Controller;
/**
 * When this command is activated, the turtle will leave a trail.
 * @author dylanpowers
 *
 */
public class PenDown implements Command {

	/**
	 * Empty constructor
	 */
	public PenDown() { }
	
	/**
	 * Put the pen down so that the turtle leaves a trail.
	 * @return 1
	 */
	@Override
	public double execute(Controller controller) {
		controller.togglePen(true);
		return 1;
	}
}
