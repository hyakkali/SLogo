package command;
/**
 * Move the turtle to the left.
 * @author dylanpowers
 *
 */
public class Left implements Command {

	private Integer amount;
	
	/**
	 * Specifies amount to move the turtle left.
	 * @param amount the amount to move.
	 */
	public Left(Integer amount) {
		this.amount = amount;
	}
	
	public void execute(Controller controller) {
		// TODO implement execute for Left
	}
}
