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
	
	public TurtleViewController(UserScreen view, Turtle turtle){
		this.view = view;
		this.turtle = turtle;
	}
	
	@Override
	public void setTurtleXLocation(double xCoord) {
		turtle.setXPosition(xCoord);
	}
	
	@Override
	public void setTurtleYLocation(double yCoord) {
		turtle.setYPosition(yCoord);
	}
	
	@Override
	public void moveTurtle(double amount) {
		turtle.move(turtle.getRotate(), amount);
	}
	
	@Override
	public void rotateTurtle(double heading) {
		turtle.rotate(heading);
	}
	
	@Override
	public void setTurtleHeading(double heading) {
		turtle.setHeading(heading);
	}
	
	@Override
	public void setTurtleTowards(double xCoord, double yCoord) {
		turtle.setTowards(xCoord,yCoord);
	}
	
	@Override
	public void togglePen(boolean penBoolean) {
		turtle.togglePenUpOrDown(penBoolean);
	}
	
	@Override
	public void setViewPenColor(Color color) {
		turtle.setPenColor(color);
	}
	
	@Override
	public void clearTurtleLines() {
		turtle.clearLines();
	}

	@Override
	public void resetTurtlePosition() {
		turtle.resetLocation();
	}
	
	@Override
	public void toggleTurtleDisplay(boolean showTurtle) {
		turtle.toggleTurtle(showTurtle);
	}
	
	//display commands
	
	@Override
	public void setViewBackgroundColor(Color color) {
		view.setBackgroundColor(color);
	}
	
	//non-command display methods
	@Override
	public void addPreviouslyRunCommand(String command) {
		view.addPreviousCommand(command);
	}
	
	@Override
	public void addExistingVariable(String variable) {
		view.addVariable(variable);
	}
	
	//misc 
	@Override
	public void displayResult(String result) {
		view.printToScreen(result);
	}
	
	@Override
	public double getTurtleHeading() {
		return turtle.getRotate();
	}
	
	@Override
	public double getTurtleXLocation() {
		return turtle.getLayoutX();
	}
	
	@Override
	public double getTurtleYLocation() {
		return turtle.getLayoutY();
	}
	
	@Override
	public boolean getIsPen() {
		return turtle.getPenBoolean();
	}
	
	@Override
	public boolean getIsTurtle() {
		return turtle.getTurtleBoolean();
	}

}
