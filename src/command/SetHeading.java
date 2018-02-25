package command;
/**
 * Change the turtle's orientation
 * @author dylanpowers
 *
 */
public class SetHeading implements Command {

	private Double heading;
	
	/**
	 * Specifies amount to move the turtle right.
	 * @param amount the amount to move.
	 */
	public SetHeading(Double heading) {
		this.heading = heading;
	}
	
	public void execute(Controller controller) {
		// TODO implement execute for Left
	}
}
