package command;
/**
 * Move the turtle to the right.
 * @author dylanpowers
 *
 */
public class Right implements Command {

	private Integer amount;
	
	/**
	 * Specifies amount to move the turtle right.
	 * @param amount the amount to move.
	 */
	public Right(Integer amount) {
		this.amount = amount;
	}
	
	public void execute(Controller controller) {
		// TODO implement execute for Left
	}
}
