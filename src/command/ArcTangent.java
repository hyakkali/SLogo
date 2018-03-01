package command;

import controller.Controller;
/**
 * Finds the inverse tangent of a given angle
 * @author dylanpowers
 *
 */
public class ArcTangent implements Command {

	private Double degrees;
	
	/**
	 * Initializes degrees
	 */
	public ArcTangent(Double degrees) {
		this.degrees = degrees;
	}
	
	/**
	 * Finds the tangent of a given angle
	 * @return the tangent of the angle represented by "degrees"
	 */
	@Override
	public double execute(Controller controller) {
		// Math.atan2() takes radians as a parameter, so change degrees to radians
		return Math.atan(Math.toRadians(this.degrees));
	}
}
