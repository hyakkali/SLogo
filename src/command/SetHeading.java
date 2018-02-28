package command;

import main.Controller;

/**
 * Change the turtle's orientation to an absolute heading
 * @author dylanpowers
 *
 */
public class SetHeading implements Command {

	private Double heading;
	
	/**
	 * Specifies amount to move the turtle right.
	 *  amount the amount to move.
	 */
	public SetHeading(Double heading) {
		this.heading = heading;
	}
	
	/**
	 * Turn turtle to an absolute heading
	 * @return number of degrees moved
	 */
	@Override
	public double execute(Controller controller) {
		// we need to return number of degrees moved, so subtract the current heading from the desired absolute heading
		double degreesMoved = this.heading - controller.getTurtleHeading();
		controller.setTurtleHeading(this.heading);
		return Math.abs(degreesMoved);
	}

}
