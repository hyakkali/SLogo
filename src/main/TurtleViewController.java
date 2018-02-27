package main;
import javafx.scene.paint.Color;
import turtle.Turtle;
import user_interface.UserScreen;
import backend.SLogoModel;

/**
 * 
 * @author Hemanth Yakkali
 * Primary controller class that manages flow of data between Command (model) and UserInterface (view)
 */
public class TurtleViewController implements Controller {
	
	private UserScreen view;
	private Turtle turtle;
	private SLogoModel model;
	
	public TurtleViewController(UserScreen view, Turtle turtle, SLogoModel slogoModel){
		this.view = view;
		this.turtle = turtle;
		this.model = slogoModel;
		this.model.setController(this);
	}
	
	//turtle commands
	/**
	 * 
	 * @param xCoord Desired Y Coordinate of the turtle
	 */
	@Override
	public void setTurtleXLocation(double xCoord) {
		turtle.setXPosition(xCoord);
	}
	
	/**
	 * 
	 * @param yCoord Desired Y Coordinate of the turtle
	 */
	@Override
	public void setTurtleYLocation(double yCoord) {
		turtle.setYPosition(yCoord);
	}
	
	/**
	 * @param amount Amount of pixels to move turtle
	 */
	@Override
	public void moveTurtle(double amount) {
		turtle.move(turtle.getRotate(), amount);
	}
	
	/**
	 * @param heading Amount of degrees to rotate turtle
	 */
	@Override
	public void rotateTurtle(double heading) {
		turtle.rotate(heading);
	}
	
	/**
	 * @param heading Desired heading of turtle
	 */
	@Override
	public void setTurtleHeading(double heading) {
		turtle.setHeading(heading);
	}
	
	/**
	 * @param xCoord X coordinate to set turtle towards
	 * @param yCoord Y coordinate to set turtle towards
	 */
	@Override
	public void setTurtleTowards(double xCoord, double yCoord) {
		turtle.setTowards(xCoord,yCoord);
	}
	
	/**
	 * Toggles the pen 
	 * @param penBoolean Boolean of true or false
	 */
	@Override
	public void togglePen(boolean penBoolean) {
		turtle.togglePenUpOrDown(penBoolean);
	}
	
	/**
	 * 
	 * @param color Color of the pen
	 */
	@Override
	public void setViewPenColor(Color color) {
		turtle.setPenColor(color);
	}
	
	/**
	 * Removes all the lines that the turtle has drawn
	 */
	@Override
	public void clearTurtleLines() {
		turtle.clearLines();
	}
	
	/**
	 * Sets turtle back to (0,0)
	 */
	@Override
	public void resetTurtlePosition() {
		turtle.resetLocation();
	}
	
	/**
	 * Toggles display of turtle
	 */
	@Override
	public void toggleTurtleDisplay(boolean showTurtle) {
		turtle.toggleTurtle(showTurtle);
	}
	
	//display commands
	
	/**
	 * 
	 * @param color Background color of the interface
	 */
	@Override
	public void setViewBackgroundColor(Color color) {
		view.setBackgroundColor(color);
	}
	
	//non-command display methods
	/**
	 * 
	 * @param command Original command String that user typed in
	 */
	@Override
	public void addPreviouslyRunCommand(String command) {
		view.addPreviousCommand(command);
	}
	
	/**
	 * 
	 * @param variable String name of any variable that has been instantiated
	 */
	@Override
	public void addExistingVariable(String variable) {
		view.addVariable(variable);
	}
	
	//misc 
	/**
	 * 
	 * @param result String result of a math operation
	 * Triggers view to print result to the screen
	 */
	@Override
	public void displayResult(String result) {
		view.printToScreen(result);
	}
	
	/**
	 * @return Current heading of the turtle
	 */
	@Override
	public double getTurtleHeading() {
		return turtle.getRotate();
	}
	
	/**
	 * @return X coordinate of the turtle
	 */
	@Override
	public double getTurtleXLocation() {
		return turtle.getLayoutX();
	}
	
	/**
	 * @return Y coordinate of the turtle
	 */
	@Override
	public double getTurtleYLocation() {
		return turtle.getLayoutY();
	}
	
	/**
	 * @return Whether or not pen is down
	 */
	@Override
	public boolean getIsPen() {
		return turtle.getPenBoolean();
	}
	
	/**
	 * @return Whether or not turtle is visible
	 */
	@Override
	public boolean getIsTurtle() {
		return turtle.getTurtleBoolean();
	}

}
