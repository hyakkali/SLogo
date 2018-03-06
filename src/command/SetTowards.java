
//package command;
//
//import controller.Controller;
//
///**
// * Sets the turtle to face a certain place on the screen.
// * @author dylanpowers
// *
// */
//public class SetTowards implements Command {
//
//	private Double x;
//	private Double y;
//	/**
//	 *
//	 * @param x x coordinate that turtle should face
//	 * @param y y coordinate that turtle should face
//	 */
//	public SetTowards(Double y, Double x) {
//		// because of the way the parser works, y is fed in first and x is fed in second
//		this.x = x;
//		this.y = y;
//	}
//
//	/**
//	 * Makes the turtle face the location (x, y)
//	 * @return number of degrees the turtle turned
//	 */
//	@Override
//	public double execute(Controller controller) {
