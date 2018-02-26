package turtle;
import java.util.Stack;

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
	private Stack<Line> lines;
	
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
	
	/**
	 * String of path to an image file
	 */
	private String imageURL = "TMNT.png";

	/**
	 * Image object of the turtle
	 */
	private Image turtleImage = new Image(getClass().getClassLoader().getResourceAsStream("TMNT.png"));
	
	/**
	 * Turtle constructor that sets X and Y coordinates and heading to 0, sets 
	 * image of the turtle to the default image.
	 */
	public Turtle(){
		super(); //sets image found in url
		this.setLayoutX(0.0);
		this.setLayoutY(0.0);
		this.setRotate(0.0);
		this.setImage(turtleImage);
		this.penBoolean = true;
		this.penColor = Color.BLACK;
		this.lines = new Stack<>();
		Line newLine = new Line(0,0,0,0);
		newLine.setFill(penColor);
		lines.add(newLine);
	}
	
	//turtle commands
	/**
	 * 
	 * @param angle Heading of the turtle
	 * @param amount Amount of pixels to move
	 */
	public void move(double angle, double amount) {
		double xAmount = calculateXAmount(angle,amount);
		double yAmount = calculateYAmount(angle,amount);
		this.setLayoutX(this.getXLocation()+xAmount);
		this.setLayoutY(this.getYLocation()+yAmount);
		setStartLineLocation();
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
	public void resetLocation() {
		this.setLayoutX(0.0);
		this.setLayoutY(0.0);
	}
	
	/**
	 * 
	 * @param xCoordinate X coordinate of the turtle
	 */
	public void setXPosition(double xCoordinate) {
		this.setLayoutX(xCoordinate);
	}
	
	/**
	 * 
	 * @param yCoordinate Y coordinate of the turtle
	 */
	public void setYPosition(double yCoordinate) {
		this.setLayoutY(yCoordinate);
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
		double currHeading = this.getRotate() % 360; //coterminal angle
		this.setRotate(currHeading+calculateAngle(xCoord,yCoord));
	}
	
	/**
	 * Sets image of the turtle
	 * @param url String path to an image
	 * 
	 */
	public void setImage(String url) {
		imageURL = url;
		this.setImage(turtleImage);
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
	 * @return X coordinate of the turtle
	 */
	public double getXLocation() {
		return this.getLayoutX();
	}
	
	/**
	 * 
	 * @return Y coordinate of the turtle
	 */
	public double getYLocation() {
		return this.getLayoutY();
	}
	
	/**
	 * 
	 * @return Current heading of the turtle
	 */
	public double getAngle() {
		return this.getRotate();
	}
	
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
	public Line getLastLine(){
		return lines.peek();
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
		return amount*Math.cos(Math.toRadians(angle));
	}
	
	/**
	 * 
	 * @param angle Heading of the turtle
	 * @param amount Amount of pixels to move turtle
	 * @return Amount of pixel change in the y direction
	 */ 
	private double calculateYAmount(double angle, double amount) {
		return amount*Math.sin(Math.toRadians(angle));
	} 
	
	/**
	 * 
	 * @param xCoord X Coordinate
	 * @param yCoord Y Coordinate
	 * @return Degree measure turtle needs to rotate to face (xCoord,yCoord)
	 */
	private double calculateAngle(double xCoord, double yCoord) {
		double currHeading = this.getRotate() % 360.0; //get coterminal angle
		double newHeading = Math.atan(yCoord/xCoord);
		return currHeading-newHeading;
	}
	
	//lines
	/**
	 * Sets the start X and start Y coordinate for the next line to be drawn
	 */
	private void setStartLineLocation() {
		if(penBoolean) { //if pen is down
			xStartLineLocation = lines.peek().getEndX();
			yStartLineLocation = lines.peek().getEndY();
		} else {
			xStartLineLocation = this.getLayoutX();
			yStartLineLocation = this.getLayoutY();
		}
	}

	/**
	 * 
	 * @param xAmount Amount pixel change in x direction
	 * @param yAmount Amount pixel change in y direction
	 */
	private void drawLine(double xAmount, double yAmount) {
		if(penBoolean) {
			Line newLine = new Line(xStartLineLocation,yStartLineLocation,xStartLineLocation+xAmount,yStartLineLocation+yAmount);
			newLine.setFill(penColor);
			lines.push(newLine);
		}
	}
	
	/**
	 * Clears all the lines that were drawn by the turtle
	 */
	public void clearLines() {
		lines.clear();
	}
	
}
