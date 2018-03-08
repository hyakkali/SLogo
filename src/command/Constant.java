package command;

import controller.Controller;

/**
 * Class to represent any constant that is part of a command.
 * @author dylanpowers
 *
 */
public class Constant implements Command {

	private double value;
	
	/**
	 * Initialize
	 */
	public Constant(double value) {
		this.value = value;
	}
	
	/**
	 * Nothing to do here, just return the value
	 */
	@Override
	public double execute(Controller controller) {
		return this.value;
	}

}
