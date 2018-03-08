
//package command;
//
//import controller.Controller;
//
///**
// * Move the turtle to a specified position on the screen.
// * @author dylanpowers
// *
// */
//public class SetPosition implements Command {
//
//	private Double x;
//	private Double y;
//
//	/**
//	 *
//	 * @param x x coordinate to move to
//	 * @param y y coordinate to move to
//	 */
//	public SetPosition(Double y, Double x) {
//		// because of the way the parser works, y is fed in first and x is fed in second
//		this.x = x;
//		this.y = y;
//	}
//
//	/**
//	 * Move the turtle to the location (x, y)
//	 * @return distance that turtle moved
//	 */
//	@Override
//	public double execute(Controller controller) {
//		// calculate distance using distance formula
