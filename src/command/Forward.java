package command;

import controller.Controller;

/**
 * Command to move the turtle forward.
 * @author dylanpowers
 *
 */
public class Forward implements Command {

	private Double amount;
	
	/**
	 * Specifies that the turtle should move forward a certain amount
	 * @param amount
	 */
	public Forward(Double amount) {
		this.amount = amount;
	}
	
	/**
	 * Executes this command by moving the turtle forward by amount
	 * @return the value in pixels that the turtle moved
	 */
	@Override
	public double execute(Controller controller) {
		// System.out.println(this.amount);
		controller.moveTurtle(this.amount);
		return this.amount;
	}


}
