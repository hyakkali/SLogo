package command;

import controller.Controller;

/**
 * Command to move the turtle backward.
 * @author dylanpowers
 *
 */
public class Backward implements Command {

	private Double amount;
	
	/**
	 * Specifies amount to move the turtle backward
	 */
	public Backward(Double amount) {
		this.amount = amount;
	}
	
	/**
	 * Move the turtle backward by the specified amount
	 * @return the number of pixels that the turtle moved backwards
	 */
	@Override
	public double execute(Controller controller) {
		// make amount negative to reflect backward movement
		controller.moveTurtle(-this.amount);
		return this.amount;
	}

}
