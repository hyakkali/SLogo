package command;

import main.Controller;

/**
 * Command to move the turtle backward.
 * @author dylanpowers
 *
 */
public class Backward implements Command {

	private Integer amount;
	
	/**
	 * Specifies amount to move the turtle backward
	 */
	public Backward(Integer amount) {
		this.amount = amount;
	}
	
	public void execute(Controller controller) {
		// TODO Backward implementation
	}
}
