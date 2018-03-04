package turtle;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pen.Pen;

/**
 * 
 * @author Hemanth Yakkali
 * Turtle object that lies in the View part of the project.
 */
public class Turtle extends ImageView{

	private HashMap<String, Image> images;

	private final double ORIGIN = 250.0;

	private final double HALF_PI_SHIFT = 90.0;

	private final double PI_SHIFT = 180.0;

	private double turtleID;

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

	public Pen pen;

	/**
	 * Turtle constructor that sets X and Y coordinates and heading to 0, sets 
	 * image of the turtle to the default image.
	 */
	public Turtle(Pen pen){
		super();
		initializeImages();
		this.setImage("Turtle");
		this.setFitHeight(TURTLE_HEIGHT);
		this.setFitWidth(TURTLE_WIDTH);
		this.pen = pen;
		setToOrigin();
	}

	/**
	 * 
	 * @param angle Heading of the turtle
	 * @param amount Amount of pixels to move
	 */
	public void move(double angle, double amount) {
		pen.setStartLineLocation(this.getX()+(TURTLE_WIDTH/2), this.getY()+TURTLE_HEIGHT);
		double xAmount = calculateXAmount(angle,amount);
		double yAmount = calculateYAmount(angle,amount);
		this.setX(this.getX()+xAmount);
		this.setY(this.getY()-yAmount);
		pen.drawLine(xAmount, yAmount);
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
		if(xCoord>0 && yCoord==0) {
			this.setRotate(HALF_PI_SHIFT);
		} else if(xCoord<0 && yCoord==0) {
			this.setRotate(PI_SHIFT+HALF_PI_SHIFT);
		} else if(xCoord==0 && yCoord>0) {
			this.setRotate(0);
		} else if(xCoord==0 && yCoord<0) {
			this.setRotate(PI_SHIFT);
		} else if(xCoord<0 && yCoord<0){
			this.setRotate(currHeading-calculateAngle(xCoord,yCoord)+HALF_PI_SHIFT);
		} else if(xCoord<0 && yCoord>0){
			this.setRotate(currHeading-calculateAngle(xCoord,yCoord)+PI_SHIFT);
		} else if(xCoord>0 && yCoord<0) {
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
