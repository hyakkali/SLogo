package command;

import main.Controller;

/**
 * Move the turtle to the right.
 * @author dylanpowers
 *
 */
public class Right implements Command {

	private Double degrees;
	
	/**
	 * Specifies amount to move the turtle right.
	 * @param amount the amount to move.
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
		// JavaFX specifies that CW is positive, so leave "degrees" as is
		controller.rotateTurtle(this.degrees);
		return this.degrees;
	}
}
