package main;

import javafx.scene.paint.Color;

public interface Controller {
	
	public void setTurtleXLocation(double amount);
	
	public void setTurtleYLocation(double amount);
	
	public void moveTurtle(double amount);
	
	public void rotateTurtle(double heading);
	
	public void setTurtleHeading(double heading);
	
	public void setTurtleTowards(double xCoord, double yCoord);
	
	public void togglePen(boolean penBoolean);
	
	public void setViewPenColor(Color color);
	
	public void toggleTurtleDisplay(boolean showTurtle);
	
	public void setViewBackgroundColor(Color color);
	
	public void addPreviouslyRunCommand(String command);
	
	public void addExistingVariable(String variable);
	
	public void displayResult(String result);
	
}
