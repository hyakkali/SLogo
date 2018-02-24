
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
		
	/**
	 * 
	 * @param command Original command String that user typed in
	 */
	public void addPreviouslyRunCommand(String command) {
		view.addPreviousCommand(command);
	}
	
	/**
	 * 
	 * @param variable 
	 */
	public void addExistingVariable(String variable) {
		view.addVariable(variable);
	}
	
	/**
	 * 
	 * @param turtle Turtle object
	 */
	public void whichTurtle(Turtle turtle) {
		view.setTurtle(turtle);
	}
	
}
