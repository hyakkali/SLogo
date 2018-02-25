package command;

import turtle.Turtle;
/**
 * Command to move the turtle forward.
 * @author dylanpowers
 *
 */
public class Forward implements Command {

	private Integer amount;
	
	/**
	 * Specifies that the turtle should move forward a certain amount
	 * @param amount
	 */
	public Forward(Integer amount) {
		this.amount = amount;
	}
	
	/**
	 * Executes this command by moving the turtle forward by amount
	 */
	@Override
	public void execute(Controller controller) {
		// TODO make turtle move forward by amount
		
	}

}
