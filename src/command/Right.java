package command;

import controller.Controller;

/**
 * Move the turtle to the right.
 * @author dylanpowers
 *
 */
public class Right implements Command {

	private Double degrees;
	
	/**
	 * Specifies amount to move the turtle right.
	 * param  the amount to move.
	 */
	public Right(Double degrees) {
		this.degrees = degrees;
	}
	
	/**
	 * Rotates the turtle clockwise
	 * @return the number of degrees turned
	 */
	@Override
	public double execute(Controller controller) {
		controller.rotateTurtle(this.degrees);
		System.out.println("hemanth"+degrees);
		return this.degrees;
	}
}
