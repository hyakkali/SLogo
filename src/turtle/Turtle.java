package turtle;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pen.LinePen;
import pen.Pen;

/**
 * 
 * @author Hemanth Yakkali
 * Turtle object that lies in the View part of the project.
 */
public class Turtle extends ImageView{

	private HashMap<String, Image> images;

	private final double ORIGIN = 250;

	private final double HALF_PI_SHIFT = 90.0;
	
	private double xEndLoc;
	
	private double yEndLoc;
	
	private double xSpeed;
	
	private double ySpeed;

	private double turtleID;

	/**
	 * String of path to an image file
	 */
	private String imageURL = "TMNT.png";
	
	private boolean isActive;

	private final int TURTLE_HEIGHT = 40;
	private final int TURTLE_WIDTH = 40;

	public Pen pen;

	/**
	 * Turtle constructor that sets X and Y coordinates and heading to 0, sets 
	 * image of the turtle to the default image.
	 */
	public Turtle(Pen pen, double Id){
		super();
		initializeImages();
		this.setImage("Turtle");
		setToOrigin();
		this.setFitHeight(TURTLE_HEIGHT);
		this.setFitWidth(TURTLE_WIDTH);
		this.pen = pen;
		this.isActive = true;
		this.turtleID = Id;
		this.xEndLoc = ORIGIN;
		this.yEndLoc = ORIGIN;
		this.xSpeed = 30;
		this.ySpeed = 30;
		setToOrigin();
	}


	/**
	 * 
	 * @param angle Heading of the turtle
	 * @param amount Amount of pixels to move
	 */
	public void move(double angle, double amount) {
		System.out.println(amount);
		pen.setStartLineLocation(this.getX()+(TURTLE_WIDTH/2), this.getY()+TURTLE_HEIGHT);
		double xAmount = calculateXAmount(angle,amount);
		double yAmount = calculateYAmount(angle,amount);
		if(amount<0||angle<0) {
			this.xSpeed = -1*Math.abs(xSpeed);
			this.ySpeed = -1*Math.abs(xSpeed);
		} else {
			this.xSpeed = Math.abs(ySpeed);
			this.ySpeed = Math.abs(ySpeed);
		}
		this.xEndLoc = this.getX()+xAmount;
		this.yEndLoc = this.getY()-yAmount;
		pen.drawLine(xAmount, yAmount);
	}
	
	public double getXEnd() {
		return this.xEndLoc;
	}
	
	public double getYEnd() {
		return this.yEndLoc;
	}
	
	public double getXSpeed() {
		return this.xSpeed;
	}
	
	public double getYSpeed() {
		return this.ySpeed;
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

	public void setID(double id) {
		turtleID = id;
	}

	public void setActive(boolean bool) {
		this.isActive = bool;
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
		if(xCoord<0 && yCoord==0) {
			this.setRotate(3*HALF_PI_SHIFT);
		} else if(xCoord==0 && yCoord>0) {
			this.setRotate(0);
		} else if(xCoord<=0 && yCoord<0){
			this.setRotate(currHeading-calculateAngle(xCoord,yCoord)+HALF_PI_SHIFT);
		} else if(xCoord<=0 && yCoord>0){
			this.setRotate(currHeading-calculateAngle(xCoord,yCoord)+2*HALF_PI_SHIFT);
		} else if(xCoord>0 && yCoord<=0) {
			this.setRotate(currHeading-calculateAngle(xCoord,yCoord)+HALF_PI_SHIFT);
		} else {
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
	public void toggleTurtle(boolean bool) {
		this.setVisible(bool);
	}

	/**
	 * 
	 * @return Current path to the image file being used 
	 */
	public String getImageURL() {
		return imageURL;
	}
	
	public boolean getActive() {
		return this.isActive;
	}
	
	public double getID() {
		return this.turtleID;
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
		return Math.floor(amount*Math.sin(Math.toRadians(angle)));
	}

	/**
	 * 
	 * @param angle Heading of the turtle
	 * @param amount Amount of pixels to move turtle
	 * @return Amount of pixel change in the y direction
	 */ 
	private double calculateYAmount(double angle, double amount) {
		return Math.floor(amount*Math.cos(Math.toRadians(angle)));
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


	/**
	 * Clears all the lines that were drawn by the turtle
	 */

	private void initializeImages() {
		images= new HashMap<String, Image>();
		ResourceBundle imageFile = ResourceBundle.getBundle("resources.languages/TurtleImages");
		for(String k:imageFile.keySet())
		{
			Image turtle = new Image("File:images/"+imageFile.getString(k));
			images.put(k,turtle);
		}
	}


	public Turtle clone()
	{
		Pen copyPen = new LinePen();
		copyPen.setPenColor(this.pen.getPenColor());
		copyPen.setPenWidth(this.pen.getPenWidth());
		copyPen.setStartLineLocation(this.getX(),this.getY());

		Turtle copy = new Turtle(copyPen,this.getID());
		copy.setImage(this.getImage());
		copy.setX(this.getX());
		copy.setY(this.getY());
		copy.setRotate(this.getRotate());
		copy.setVisible(this.isVisible());

		return copy;
	}

}
