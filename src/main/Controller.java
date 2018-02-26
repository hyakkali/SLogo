package main;
import turtle.Turtle;

/**
 * 
 * @author Hemanth Yakkali
 * Primary controller class that manages flow of data between Command (model) and UserInterface (view)
 */
public class Controller {
	
	private UserInterface view;
	private Command model;
	private Turtle turtle;
	
	public Controller(UserInterface view, Command model, Turtle turtle){
		this.view = view;
		this.model = model;
		this.turtle = turtle;
	}
	
	//turtle commands
	/**
	 * 
	 * @param amount Amount turtle needs to move horizontally
	 */
	public void setTurtleXLocation(double amount) {
		turtle.setXPosition(amount);
	}
	
	/**
	 * 
	 * @param amount Amount turtle needs to move vertically
	 */
	public void setTurtleYLocation(double amount) {
		turtle.setYPosition(amount);
	}
		
	public void moveTurtle(double heading, double amount) {
		turtle.move(heading, amount);
	}
		
	public void rotateTurtle(double heading) {
		turtle.rotate(heading);
	}
	
	public void setTurtleHeading(double heading) {
		turtle.setHeading(heading);
	}
	
	/**
	 * Toggles the pen 
	 * @param penBoolean Boolean of true or false
	 */
	public void togglePen(boolean penBoolean) {
		turtle.togglePenUpOrDown(penBoolean);
	}
	
	//display commands
	
	public void toggleTurtleDisplay(boolean showTurtle) {
		view.toggleTurtle(showTurtle);
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
	 * @param color Color of the pen
	 */
	public void setViewPenColor(Color color) {
		view.setPenColor(color);
	}
	
	//non-command display methods
	/**
	 * 
	 * @param command Original command String that user typed in
	 */
	public void addPreviouslyRunCommand(String command) {
		view.addPreviousCommand(command);
	}
	
	/**
	 * 
	 * @param variable String name of any variable that has been instantiated
	 */
	public void addExistingVariable(String variable) {
		view.addVariable(variable);
	}
	
	//misc 
	/**
	 * 
	 * @param result String result of a math operation
	 * Triggers view to print result to the screen
	 */
	public void displayResult(String result) {
		view.printToScreen(result);
	}
	
}
