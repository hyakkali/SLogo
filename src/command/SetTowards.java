package command;
/**
 * Sets the turtle to face a certain place on the screen.
 * @author dylanpowers
 *
 */
public class SetTowards implements Command {

	private Integer x;
	private Integer y;
	/**
	 * 
	 * @param x x coordinate that turtle should face
	 * @param y y coordinate that turtle should face
	 */
	public SetTowards(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Makes the turtle face the location (x, y)
	 */
	public void execute(Controller controller) {
		// TODO implementation
	}
}
