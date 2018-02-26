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
	
	private boolean penBoolean;
	
	private Stack<Line> lines;
	
	private Color penColor;
	
	/**
	 * String of path to an image file
	 */
	private String imageURL;
	
	/**
	 * Image object of the turtle
	 */
	private Image turtleImage = new Image(getClass().getClassLoader().getResourceAsStream(imageURL));
	
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
		drawLine(xAmount,yAmount);
	}
	
	//setters
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
	
	public void setTowards(double xCoord, double yCoord) {
		double currHeading = this.getRotate() % 360; //coterminal angle
		this.setRotate(currHeading+calculateAngle(xCoord,yCoord));
	}
	
	public void rotate(double heading) { //can be ccwise or cwise
		this.setRotate(this.getRotate()+heading);
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
	
	public void togglePenUpOrDown(boolean bool) {
		penBoolean = bool;
	}
	
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
	
	public Line getLastLine(){
		return lines.peek();
	}
	
	public Color getPenColor() {
		return penColor;
	}
	
	public boolean getPenBoolean() {
		return penBoolean;
	}
	
	//misc
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
	
	private double calculateAngle(double xCoord, double yCoord) {
		double currHeading = this.getRotate() % 360.0; //get coterminal angle
		double newHeading = Math.atan(yCoord/xCoord);
		return currHeading-newHeading;
	}
	
	//lines
	
	private void drawLine(double xAmount, double yAmount) {
		if(penBoolean) {
			Line lastLine = lines.peek();
			double startX = lastLine.getEndX();
			double startY = lastLine.getEndY();
			Line newLine = new Line(startX,startY,startX+xAmount,startY+yAmount);
			newLine.setFill(penColor);
			lines.push(newLine);
		}
	}
	
}
