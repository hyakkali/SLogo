package command;

import main.Controller;
/**
 * Move the turtle to the left.
 * @author dylanpowers
 *
 */
public class Left implements Command {

	private Double degrees;
	
	/**
	 * Specifies amount to move the turtle left.
	 * @param amount the amount to move.
	 */
	public Left(Double degrees) {
		this.degrees = degrees;
	}
	
	/**
	 * Rotates the turtle counterclockwise
	 * @return the number of degrees turned
	 */
	@Override
	public double execute(Controller controller) {
		// JavaFX specifies that CCW is negative, so negate "degrees" to reflect this
		controller.rotateTurtle(-this.degrees);
		return this.degrees;
	}
}
