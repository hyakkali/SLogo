package controller;
import backend.SLogoModel;
import javafx.scene.paint.Color;
import turtle.Turtle;
import userinterface.UserScreen;
import backend.SLogoData;
import pen.LinePen;
import java.util.ArrayList;


/**
 * 
 * @author Hemanth Yakkali
 * Primary controller class that manages flow of data between Command (model) and UserInterface (view)
 */
public class Controller {

	private UserScreen view;
	private ArrayList<Turtle> turtles;
	private SLogoModel slogoModel;
	private ArrayList<Turtle> activeTurtles;

	public Controller(UserScreen view, ArrayList<Turtle> turtles){
		this.view = view;
		this.turtles = turtles;
	}
	public void initSlogo(SLogoModel s)
	{
		slogoModel=s;
	}

	/**
	 * 
	 * @param xCoord Desired Y Coordinate of the turtle
	 */
	public void setTurtleXLocation(double xCoord) {
		for(Turtle turtle: view.myActiveTurtles) {
			turtle.setXPosition(xCoord);
		}
	}

	/**
	 * 
	 * @param yCoord Desired Y Coordinate of the turtle
	 */
	public void setTurtleYLocation(double yCoord) {
		for(Turtle turtle: view.myActiveTurtles) {
			turtle.setYPosition(yCoord);
		}
	}

	/**
	 * 
	 * @param id Double id to identify a turtle
	 */
	public void setTurtleID(double id) {
		for(Turtle turtle: turtles) {
			turtle.setID(id);
		}
	}

	/**
	 * @param amount Amount of pixels to move turtle
	 */
	public void moveTurtle(double amount) {
		for(Turtle turtle: view.myActiveTurtles) {
			turtle.move(turtle.getRotate(), amount);
		}
	}

	/**
	 * @param heading Amount of degrees to rotate turtle
	 */
	public void rotateTurtle(double heading) {
		for(Turtle turtle: view.myActiveTurtles) {
			turtle.rotate(heading);
		}
	}

	/**
	 * @param heading Desired heading of turtle
	 */
	public void setTurtleHeading(double heading) {
		for(Turtle turtle: view.myActiveTurtles) {
			turtle.setHeading(heading);
		}
	}

	/**
	 * @param xCoord X coordinate to set turtle towards
	 * @param yCoord Y coordinate to set turtle towards
	 */
	public void setTurtleTowards(double xCoord, double yCoord) {
		for(Turtle turtle: view.myActiveTurtles) {
			turtle.setTowards(xCoord,yCoord);
		}
	}

	/**
	 * Toggles the pen 
	 * @param penBoolean Boolean of true or false
	 */
	public void togglePen(boolean penBoolean) {
		for(Turtle turtle: turtles) {
			turtle.pen.togglePenUpOrDown(penBoolean);
		}
	}

	/**
	 * 
	 * @param color Color of the pen
	 */
	public void setViewPenColor(Color color) {
		for(Turtle turtle: turtles) {
			turtle.pen.setPenColor(color);
		}
	}

	public void setPenWidth(double width) {
		for(Turtle turtle: turtles) {
			turtle.pen.setPenWidth(width);
		}
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
		for(Turtle turtle: turtles) {
			turtle.setToOrigin();
		}
	}

	/**
	 * Toggles display of turtle
	 * @param showTurtle Boolean of true or false
	 */
	public void toggleTurtleDisplay(boolean showTurtle) {
		for(Turtle turtle: turtles) {
			turtle.toggleTurtle(showTurtle);
		}
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
	//	view.addPreviousCommand(command);
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
	public ArrayList<Double> getTurtleHeading() {
		ArrayList<Double> headingList = new ArrayList<>();
		for(Turtle turtle: turtles) {
			headingList.add(turtle.getRotate());
		}
		return headingList;
	}

	/**
	 * @return X coordinate of the turtle
	 */
	public ArrayList<Double> getTurtleXLocation() {
		ArrayList<Double> xCoordList = new ArrayList<>();
		for(Turtle turtle: turtles) {
			xCoordList.add(turtle.getX());
		}
		return xCoordList;
	}

	/**
	 * @return Y coordinate of the turtle
	 */
	public ArrayList<Double> getTurtleYLocation() {
		ArrayList<Double> yCoordList = new ArrayList<>();
		for(Turtle turtle: turtles) {
			yCoordList.add(turtle.getY());
		}
		return yCoordList;
	}

	/**
	 * @return Whether or not pen is down
	 */
	public ArrayList<Boolean> getIsPen() {
		ArrayList<Boolean> isPenList = new ArrayList<>();
		for(Turtle turtle: turtles) {
			isPenList.add(turtle.pen.getPenBoolean());
		}
		return isPenList;
	}

	/**
	 * @return Whether or not turtle is visible
	 */
	public ArrayList<Boolean> getIsTurtle() {
		ArrayList<Boolean> isTurtleList = new ArrayList<>();
		for(Turtle turtle: turtles) {
			isTurtleList.add(turtle.getTurtleBoolean());
		}
		return isTurtleList;
	}

	public SLogoData getMyData() {
		return slogoModel.getMyData();
	}


	public Integer getNumTurtles() {
		return turtles.size();
	}

	public void setMyColor(double colorID) {
		view.setPenColor(colorID);
	}

	public void setMyBackground(double colorID) {
		view.setBackGroundColor(colorID);
	}

	public void setMyShape(double shapeID) {
		view.setTurtleImage(shapeID);
	}

	public void setMyPenSize(double penSize) {
		for (Turtle t : turtles) {
			if (t.getActive()) {
				t.pen.setPenWidth(penSize);
			}
		}
	}

	public double getPenColor() {
		return turtles.get(0).pen.getColorIndex();
	}

	public double getShapeIndex() {
		return turtles.get(0).getImageIndex();

	}

	public double getActiveTurtle() {
		return turtles.get(0).getID();
	}

	public void addActiveTurtles(String inputName) {
		for (Turtle t: turtles) {
			if (t.getID() == Double.parseDouble(inputName)) {
				if (!view.myActiveTurtles.contains(t)) {
					view.myActiveTurtles.add(t);
				}
			}
			else {
				view.myActiveTurtles.add(new Turtle(new LinePen(), Double.parseDouble(inputName)));
			}
		}
	}

	public Turtle getAskedTurtle(String inputName) {
		for (Turtle t: turtles) {
			if (t.getID() == Double.parseDouble(inputName)) {
				return t;
			}
		}
		return null;
	}

	public void tempActiveTurtles(ArrayList<Turtle> newTurtles) {
		activeTurtles = view.myActiveTurtles;
		view.myActiveTurtles.clear();
		for (Turtle t: newTurtles) {
			view.myActiveTurtles.add(t);
		}
	}

	public void resetActiveTurtles() {
		view.myActiveTurtles.clear();
		for (Turtle t: activeTurtles) {
			view.myActiveTurtles.add(t);
		}
	}




}
