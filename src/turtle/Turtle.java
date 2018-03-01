package turtle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * 
 * @author Hemanth Yakkali
 * Turtle object that lies in the View part of the project.
 */
public class Turtle extends ImageView{

	/**
	 * Boolean for whether or not pen is down
	 */
	private boolean penBoolean;

	/**
	 * Stack of lines drawn by the turtle
	 */
	private List<Line> lines;

	/**
	 * Start X coordinate for the next line
	 */
	private double xStartLineLocation;

	/**
	 * Start Y coordinate for the next line
	 */
	private double yStartLineLocation;

	/**
	 * Color of the next line to be drawn
	 */
	private Color penColor;

	private HashMap<String, Image> images;

	private final double ORIGIN = 250.0;

	/**
	 * String of path to an image file
	 */
	private String imageURL = "TMNT.png";

	/**
	 * Image object of the turtle
	 */
	private Image turtleImage;

	private final int TURTLE_HEIGHT = 40;
	private final int TURTLE_WIDTH = 40;

	/**
	 * Turtle constructor that sets X and Y coordinates and heading to 0, sets 
	 * image of the turtle to the default image.
	 */
	public Turtle(){
		super();
		initializeImages();
		this.setImage("Turtle");
		this.setRotate(0.0);
		setToOrigin();
		this.setFitHeight(TURTLE_HEIGHT);
		this.setFitWidth(TURTLE_WIDTH);
		this.penBoolean = true;
		this.penColor = Color.BLACK;
		this.lines = new ArrayList<>();
	}

	/**
	 * 
	 * @param angle Heading of the turtle
	 * @param amount Amount of pixels to move
	 */
	public void move(double angle, double amount) {
		setStartLineLocation();
		double xAmount = calculateXAmount(angle,amount);
		double yAmount = calculateYAmount(angle,amount);
		this.setX(this.getX()+xAmount);
		this.setY(this.getY()-yAmount);
		drawLine(xAmount,yAmount);
	}

	/**
	 * 
	 * @param heading Amount of degrees to rotate turtle
	 */
	public void rotate(double heading) { //can be ccwise or cwise
		this.setRotate(this.getRotate()+heading);
	}

	/**
	 * Resets location of the turtle to (0,0)
	 */
	public void setToOrigin() {
		this.setX(ORIGIN);
		this.setY(ORIGIN);
	}

	/**
	 * 
	 * @param xCoordinate X coordinate of the turtle
	 */
	public void setXPosition(double xCoordinate) {
		this.setX(xCoordinate);
	}

	/**
	 * 
	 * @param yCoordinate Y coordinate of the turtle
	 */
	public void setYPosition(double yCoordinate) {
		this.setY(yCoordinate);
	}

	/**
	 * 
	 * @param heading Heading of the turtle
	 */
	public void setHeading(double heading) {
		this.setRotate(heading);
	}

	/**
	 * 
	 * @param xCoord X coordinate
	 * @param yCoord Y coordinate
	 */
	public void setTowards(double xCoord, double yCoord) {
		double currHeading = this.getRotate();
		System.out.println("y"+yCoord);
		if(yCoord<0) {
			this.setRotate(currHeading+calculateAngle(xCoord,yCoord));
		}else {
			this.setRotate(currHeading-calculateAngle(xCoord,yCoord));
		}
	}

	/**
	 * Sets image of the turtle
	 * @param k String mapping to an image
	 * 
	 */
	public void setImage(String k) {
		this.setImage(images.get(k));
	}

	/**
	 * 
	 * @param bool True or false boolean
	 */
	public void togglePenUpOrDown(boolean bool) {
		penBoolean = bool;
	}

	/**
	 * 
	 * @param bool True or false boolean
	 */
	public void toggleTurtle(boolean bool) {
		this.setVisible(bool);
	}

	/**
	 * 
	 * @param color Color of the line
	 */
	public void setPenColor(Color color) {
		penColor = color;
	}

	//getters
	/**
	 * 
	 * @return Current path to the image file being used 
	 */
	public String getImageURL() {
		return imageURL;
	}

	/**
	 * 
	 * @return Last line object that was drawn
	 */
	public List<Line> getLines(){
		return this.lines;
	}

	/**
	 * 
	 * @return Color of the last line drawn
	 */
	public Color getPenColor() {
		return penColor;
	}

	/**
	 * 
	 * @return Boolean if pen is down or not
	 */
	public boolean getPenBoolean() {
		return penBoolean;
	}

	/**
	 * 
	 * @return Boolean if turtle is visible or not
	 */
	public boolean getTurtleBoolean() {
		return this.isVisible();
	}

	//math
	/**
	 * 
	 * @param angle Heading of the turtle
	 * @param amount Amount of pixels to move turtle
	 * @return Amount of pixel change in the x direction
	 */ 
	private double calculateXAmount(double angle, double amount) {
		return amount*Math.sin(Math.toRadians(angle));
	}

	/**
	 * 
	 * @param angle Heading of the turtle
	 * @param amount Amount of pixels to move turtle
	 * @return Amount of pixel change in the y direction
	 */ 
	private double calculateYAmount(double angle, double amount) {
		return amount*Math.cos(Math.toRadians(angle));
	} 

	/**
	 * 
	 * @param xCoord X Coordinate
	 * @param yCoord Y Coordinate
	 * @return Degree measure turtle needs to rotate to face (xCoord,yCoord)
	 */
	private double calculateAngle(double xCoord, double yCoord) {
		double currHeading = this.getRotate();
		double newHeading = Math.toDegrees(Math.acos(xCoord/Math.sqrt(yCoord*yCoord+xCoord*xCoord)));
		System.out.println(newHeading);
		return currHeading-newHeading;
	}

	//lines
	/**
	 * Sets the start X and start Y coordinate for the next line to be drawn
	 */
	private void setStartLineLocation() {
		xStartLineLocation = this.getX()+(TURTLE_WIDTH/2);
		yStartLineLocation = this.getY()+TURTLE_HEIGHT;	
	}

	/**
	 * 
	 * @param xAmount Amount pixel change in x direction
	 * @param yAmount Amount pixel change in y direction
	 */
	private void drawLine(double xAmount, double yAmount) {
		if(penBoolean) {
			Line newLine = new Line(xStartLineLocation,yStartLineLocation,xStartLineLocation+xAmount,yStartLineLocation-yAmount);
			newLine.setStroke(penColor);
			lines.add(newLine);
		}
	}

	/**
	 * Clears all the lines that were drawn by the turtle
	 */
	public void clearLines() {
		lines.clear();
	}

	private void initializeImages() {
		images= new HashMap<String, Image>();
		ResourceBundle imageFile = ResourceBundle.getBundle("resources.languages/TurtleImages");
		for(String k:imageFile.keySet())
		{
			Image turtle = new Image("File:images/"+imageFile.getString(k));
			images.put(k,turtle);
		}
	}

}
