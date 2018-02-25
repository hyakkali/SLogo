
/**
 * 
 * @author Hemanth Yakkali
 * Primary controller class that manages flow of data between Command (model) and UserInterface (view)
 */
public class Controller {
	
	private UserInterface view;
	private Command model;
	
	public Controller(UserInterface view, Command model){
		this.view = view;
		this.model = model;
	}
	
	//turtle commands
	/**
	 * 
	 * @param amount Amount turtle needs to move horizontally
	 */
	public void setTurtleXLocation(int amount) {
		view.setXLocation(amount);
	}
	
	/**
	 * 
	 * @param amount Amount turtle needs to move vertically
	 */
	public void setTurtleYLocation(int amount) {
		view.setYLocation(amount);
	}
	
	public void toggleTurtleDisplay(boolean showTurtle) {
		view.toggleTurtle(showTurtle);
	}
	
	//display commands
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
	
	public void togglePen(boolean penBoolean) {
		view.togglePenUpOrDown(penBoolean);
	}
	
	//display methods
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
	
	/**
	 * 
	 * @param turtle Turtle object
	 */
	public void whichTurtle(Turtle turtle) {
		view.setTurtle(turtle);
	}
	
}
