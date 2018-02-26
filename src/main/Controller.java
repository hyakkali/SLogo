package main;
<<<<<<< HEAD
import command.Command;
import javafx.scene.paint.Color;
import turtle.Turtle;
import user_interface.UserScreen;

/**
 * 
 * @author Hemanth Yakkali
 * Primary controller class that manages flow of data between Command (model) and UserInterface (view)
 */
public class Controller {
	
	private UserScreen view;
	private Command model;
	private Turtle turtle;
	
	public Controller(UserScreen view, Command model, Turtle turtle){
		this.view = view;
		this.model = model;
		this.turtle = turtle;
	}
=======

import javafx.scene.paint.Color;

public interface Controller {
	
	public void setTurtleXLocation(double amount);
>>>>>>> 23d3ddc776054db93d9a2e82f77b5ca3fcdaa8a8
	
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
