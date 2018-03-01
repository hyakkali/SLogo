package command;

import controller.Controller;

/**
 * Moves the turtle to the center of the screen AND erases the trail.
 * @author dylanpowers
 *
 */
public class ClearScreen implements Command {

	private Command homeCommand;
	
	/**
	 * Initializes this command with a home command - the home command is called first.
	 */
	public ClearScreen() {
		homeCommand = new Home();
	}
	
	/**
	 * Moves the turtle to the center of the screen and erases the trail.
	 * @return distance the turtle moved
	 */
	@Override
	public double execute(Controller controller) {
		controller.clearTurtleLines();
		return this.homeCommand.execute(controller);
	}
	
}
