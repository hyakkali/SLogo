package controller;
import javafx.scene.paint.Color;
import pen.Pen;
import turtle.Turtle;
import userinterface.UserScreen;
import backend.SLogoData;
import java.util.List;
import backend.Variable;


/**
 * 
 * @author Hemanth Yakkali
 * Primary controller class that manages flow of data between Command (model) and UserInterface (view)
 */
public class Controller {

	private UserScreen view;
	private Turtle turtle;
	private Pen pen;

	public Controller(UserScreen view, Turtle turtle, Pen pen){
		this.view = view;
		this.turtle = turtle;
		this.pen = pen;
	}

	/**
	 * 
	 * @param xCoord Desired Y Coordinate of the turtle
	 */
	public void setTurtleXLocation(double xCoord) {
		turtle.setXPosition(xCoord);
	}

	/**
	 * 
	 * @param yCoord Desired Y Coordinate of the turtle
	 */
	public void setTurtleYLocation(double yCoord) {
		turtle.setYPosition(yCoord);
	}

	/**
	 * @param amount Amount of pixels to move turtle
	 */
	public void moveTurtle(double amount) {
		turtle.move(turtle.getRotate(), amount);
	}

	/**
	 * @param heading Amount of degrees to rotate turtle
	 */
	public void rotateTurtle(double heading) {
		turtle.rotate(heading);
	}

	/**
	 * @param heading Desired heading of turtle
	 */
	public void setTurtleHeading(double heading) {
		turtle.setHeading(heading);
	}

	/**
	 * @param xCoord X coordinate to set turtle towards
	 * @param yCoord Y coordinate to set turtle towards
	 */
	public void setTurtleTowards(double xCoord, double yCoord) {
		turtle.setTowards(xCoord,yCoord);
	}

	/**
	 * Toggles the pen 
	 * @param penBoolean Boolean of true or false
	 */
	public void togglePen(boolean penBoolean) {
		pen.togglePenUpOrDown(penBoolean);
	}

	/**
	 * 
	 * @param color Color of the pen
	 */
	public void setViewPenColor(Color color) {
		pen.setPenColor(color);
	}

	/**
	 * Removes all the lines that the turtle has drawn
	 */
	public void clearTurtleLines() {
//		turtle.clearLines();
//		view.removeLines();
	}

	/**
	 * Sets turtle back to (0,0)
	 */
	public void resetTurtlePosition() {
		turtle.setToOrigin();
	}

	/**
	 * Toggles display of turtle
	 * @param showTurtle Boolean of true or false
	 */
	public void toggleTurtleDisplay(boolean showTurtle) {
		turtle.toggleTurtle(showTurtle);
	}

	/**
	 * 
	 * @param color Background color of the interface
	 */
	public void setViewBackgroundColor(Color color) {
		view.setBackgroundColor(color);
	}

	/**
	 * 
	 * @param command Original command String that user typed in
	 */
	public void addPreviouslyRunCommand(String command) {
		view.addPreviousCommand(command);
	}

	/**
	 * Triggers view to print text to the screen
	 * @param text to be displayed on screen
	 * 
	 */
	public void displayText(String text) {
		view.printToScreen(text);
	}

	/**
	 * @return Current heading of the turtle
	 */
	public double getTurtleHeading() {
		return turtle.getRotate();
	}

	/**
	 * @return X coordinate of the turtle
	 */
	public double getTurtleXLocation() {
		return turtle.getLayoutX();
	}

	/**
	 * @return Y coordinate of the turtle
	 */
	public double getTurtleYLocation() {
		return turtle.getLayoutY();
	}

	/**
	 * @return Whether or not pen is down
	 */
	public boolean getIsPen() {
		return pen.getPenBoolean();
	}

	/**
	 * @return Whether or not turtle is visible
	 */
	public boolean getIsTurtle() {
		return turtle.getTurtleBoolean();
	}

	public SLogoData getMyData() {
		return view.getMyModel().getMyData();
	}

}
