package main;
import javafx.scene.paint.Color;

/**
 * 
 * @author Hemanth Yakkali
 * Primary controller class that manages flow of data between Command (model) and UserInterface (view)
 */

public interface Controller {
	
	/*
	 * Turtle-specific commands
	 */
	/**
	 * 
	 * @param xCoord Desired Y Coordinate of the turtle
	 */
	public void setTurtleXLocation(double xCoord);

	/**
	 * 
	 * @param yCoord Desired Y Coordinate of the turtle
	 */
	public void setTurtleYLocation(double yCoord);
	
	/**
	 * @param amount Amount of pixels to move turtle
	 */
	public void moveTurtle(double amount);
	
	/**
	 * @param heading Amount of degrees to rotate turtle
	 */
	public void rotateTurtle(double heading);
	
	/**
	 * @param heading Desired heading of turtle
	 */
	public void setTurtleHeading(double heading);
	
	/**
	 * @param xCoord X coordinate to set turtle towards
	 * @param yCoord Y coordinate to set turtle towards
	 */
	public void setTurtleTowards(double xCoord, double yCoord);
	
	/**
	 * Removes all the lines that the turtle has drawn
	 */
	public void clearTurtleLines();
	
	/**
	 * Sets turtle back to (0,0)
	 */
	public void resetTurtlePosition();
	
	/*
	 * Display-specific commands
	 */
	/**
	 * Toggles the pen 
	 * @param penBoolean Boolean of true or false
	 */
	public void togglePen(boolean penBoolean);
	
	/**
	 * 
	 * @param color Color of the pen
	 */
	public void setViewPenColor(Color color);
	
	/**
	 * Toggles display of turtle
	 * @param showTurtle Boolean of true or false
	 */
	public void toggleTurtleDisplay(boolean showTurtle);
	
	/**
	 * 
	 * @param color Background color of the interface
	 */
	public void setViewBackgroundColor(Color color);
	
	/**
	 * 
	 * @param command Original command String that user typed in
	 */
	public void addPreviouslyRunCommand(String command);
	
	/**
	 * 
	 * @param variable String name of any variable that has been instantiated
	 */
	public void addExistingVariable(String variable);
	
	/**
	 * Triggers view to print result to the screen
	 * @param result String result of a math operation
	 * 
	 */
	public void displayResult(String result);
	
	/*
	 * Getters
	 */
	/**
	 * @return Current heading of the turtle
	 */
	public double getTurtleHeading();
	
	/**
	 * @return X coordinate of the turtle
	 */
	public double getTurtleXLocation();
	
	/**
	 * @return Y coordinate of the turtle
	 */
	public double getTurtleYLocation();
	
	/**
	 * @return Whether or not pen is down
	 */
	public boolean getIsPen();
	
	/**
	 * @return Whether or not turtle is visible
	 */
	public boolean getIsTurtle();
	
}
