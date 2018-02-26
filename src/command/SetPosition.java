package command;

import main.Controller;

/**
 * Move the turtle to a specified position on the screen.
 * @author dylanpowers
 *
 */
public class SetPosition implements Command {

	private Integer x;
	private Integer y;
	
	/**
	 * 
	 * @param x x coordinate to move to
	 * @param y y coordinate to move to
	 */
	public SetPosition(Integer x, Integer y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Move the turtle to the location (x, y)
	 */
	public void execute(Controller controller) {
		// TODO implementation
	}
}
