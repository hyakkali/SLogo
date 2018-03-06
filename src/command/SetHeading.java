<<<<<<< HEAD
//package command;
//
//import controller.Controller;
//
///**
// * Change the turtle's orientation to an absolute heading
// * @author dylanpowers
// *
// */
//public class SetHeading implements Command {
//
//	private Double heading;
//
//	/**
//	 * Specifies amount to move the turtle right.
//	 *  amount the amount to move.
//	 */
//	public SetHeading(Double heading) {
//		this.heading = heading;
//	}
//
//	/**
//	 * Turn turtle to an absolute heading
//	 * @return number of degrees moved
//	 */
//	@Override
//	public double execute(Controller controller) {
//		// we need to return number of degrees moved, so subtract the current heading from the desired absolute heading
//		double degreesMoved = this.heading - controller.getTurtleHeading();
//		controller.setTurtleHeading(this.heading);
//		return Math.abs(degreesMoved);
//	}
//
//}
=======
package command;

import controller.Controller;

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
//		double degreesMoved = this.heading - controller.getTurtleHeading();
//		controller.setTurtleHeading(this.heading);
//		return Math.abs(degreesMoved);
		return 1;
	}

}
>>>>>>> 49cb8286f4e30b5eba95574343567d8d5de7b798
