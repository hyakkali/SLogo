package turtle;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pen.LinePen;
import pen.Pen;

/**
 * 
 * @author Hemanth Yakkali
 * Class that creates a turtle object with speed, location, and other such properties. 
 */
public class Turtle extends ImageView{

	private HashMap<String, Image> images;
	
	private HashMap<String, Double> imageKey = new HashMap<>();

	private final double ORIGIN = 250;

	private final double HALF_PI = 90.0;
	
	private final double PI = 180.0;

	private final double THREE_HALF_PI = 270.0;

	private final double TWO_PI = 360.0;

	private double xEndLoc = ORIGIN;
	
	private double yEndLoc = ORIGIN;
	
	private double xSpeed = 30;
	
	private double ySpeed = 30;

	private double turtleID;
	
    private final double ACTIVE_TURTLE = 0.0;
    
    private final double INACTIVE_TURTLE = 0.5;

	/**
	 * String of path to an image file
	 */
	private String imageURL = "TMNT";

	/**
	 * Image object of the turtle
	 */
	private Image turtleImage;
	
	private boolean isActive = true;

	private final int TURTLE_HEIGHT = 40;
	
	private final int TURTLE_WIDTH = 40;

	private double tImage = 0;

	public Pen pen;

	/**
	 * Turtle constructor that sets X and Y coordinates and heading to 0, sets 
	 * image of the turtle to the default image.
	 */
	public Turtle(Pen pen, double Id){
		super();
		initializeImages();
		this.setImage("Turtle");
		this.setFitHeight(TURTLE_HEIGHT);
		this.setFitWidth(TURTLE_WIDTH);
		this.pen = pen;
		this.turtleID = Id;
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
		if(amount<0) {
			setBackwardSpeed(angle);
		} else {
			setForwardSpeed(angle);
		}
		this.xEndLoc = this.getX()+xAmount;
		this.yEndLoc = this.getY()-yAmount;
		pen.drawLine(xAmount, yAmount);
	}
	
	/**
	 * 
	 * @param angle Heading of turtle
	 */
	private void setForwardSpeed(double angle) {
		double newAngle = getCoterminalAngle(angle);
		if(newAngle>=0 && newAngle<=HALF_PI) {
			this.xSpeed = Math.abs(xSpeed);
			this.ySpeed = Math.abs(ySpeed);
		} else if(newAngle>HALF_PI && newAngle<=PI) {
			this.xSpeed = Math.abs(xSpeed);
			this.ySpeed = -1*Math.abs(ySpeed);
		} else if(newAngle>PI && newAngle<=THREE_HALF_PI) {
			this.xSpeed = -1*Math.abs(xSpeed);
			this.ySpeed = -1*Math.abs(ySpeed);
		} else if(newAngle>THREE_HALF_PI && newAngle<=TWO_PI) {
			this.xSpeed = -1*Math.abs(xSpeed);
			this.ySpeed = Math.abs(ySpeed);
		}
	}
	
	/**
	 * 
	 * @param angle Heading of turtle
	 */
	private void setBackwardSpeed(double angle) {
		double newAngle = getCoterminalAngle(angle);
		if(newAngle>=0 && newAngle<=HALF_PI) {
			this.xSpeed = -1*Math.abs(xSpeed);
			this.ySpeed = -1*Math.abs(ySpeed);
		} else if(newAngle>HALF_PI && newAngle<=PI) {
			this.xSpeed = -1*Math.abs(xSpeed);
			this.ySpeed = Math.abs(ySpeed);
		} else if(newAngle>PI && newAngle<=THREE_HALF_PI) {
			this.xSpeed = Math.abs(xSpeed);
			this.ySpeed = Math.abs(ySpeed);
		} else if(newAngle>THREE_HALF_PI && newAngle<=TWO_PI) {
			this.xSpeed = Math.abs(xSpeed);
			this.ySpeed = -1*Math.abs(ySpeed);
		}
	}

	/**
	 * 
	 * @param heading Amount of degrees to rotate turtle
	 */
	public void rotate(double heading) { //can be ccwise or cwise
		this.setRotate(this.getRotate()+heading);
	}
	
	/**
	 * 
	 * @param value Value between 0.0 and 1.0
	 * @return ColorAdjust to adjust image
	 */
    public ColorAdjust changeImageBrightness(double value) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(value);
        return colorAdjust;
    }

	/**
	 * Resets location of the turtle to (0,0)
	 */
	public void setToOrigin() {
		this.setX(ORIGIN);
		this.setY(ORIGIN);
		this.setHeading(0.0);
		this.xEndLoc = ORIGIN;
		this.yEndLoc = ORIGIN;
	}

	/**
	 * 
	 * @param id Double ID of the turtle
	 */
	public void setID(double id) {
		turtleID = id;
	}

	/**
	 * 
	 * @param bool True (active) or false (inactive)
	 */
	public void setActive(boolean bool) {
		this.isActive = bool;
		if(bool) {
			this.setEffect(changeImageBrightness(ACTIVE_TURTLE));
		} else {
			this.setEffect(changeImageBrightness(INACTIVE_TURTLE));
		}
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
	 * @param xCoord End X coordinate 
	 */
	public void setXEnd(double xCoord) {
		this.xEndLoc = xCoord;
	}
	
	/**
	 * 
	 * @param yCoord End Y coordinate 
	 */
	public void setYEnd(double yCoord) {
		this.yEndLoc = yCoord;
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
			this.setRotate(THREE_HALF_PI);
		} else if(xCoord==0 && yCoord>0) {
			this.setRotate(0);
		} else if(xCoord<=0 && yCoord<0){
			this.setRotate(currHeading-calculateAngle(xCoord,yCoord)+HALF_PI);
		} else if(xCoord<=0 && yCoord>0){
			this.setRotate(currHeading-calculateAngle(xCoord,yCoord)+PI);
		} else if(xCoord>0 && yCoord<=0) {
			this.setRotate(currHeading-calculateAngle(xCoord,yCoord)+HALF_PI);
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
		imageURL=k;
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
	 * @return True or false boolean 
	 */
	public boolean getActive() {
		return this.isActive;
	}
	
	/**
	 * 
	 * @return ID of turtle
	 */
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
	
	/**
	 * 
	 * @return X end location of turtle
	 */
	public double getXEnd() {
		return this.xEndLoc;
	}
	
	/**
	 * 
	 * @return Y end location of turtle
	 */
	public double getYEnd() {
		return this.yEndLoc;
	}
	
	/**
	 * 
	 * @return X speed of turtle
	 */
	public double getXSpeed() {
		return this.xSpeed;
	}
	
	/**
	 * 
	 * @return Y speed of turtle
	 */
	public double getYSpeed() {
		return this.ySpeed;
	}
	
	/**
	 * 
	 * @return Image index of the current turtle image
	 */
	public double getImageIndex() {
		return imageKey.get(imageURL);
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
		return currHeading-newHeading;
	}
	
	/**
	 * 
	 * @param angle Heading of turtle
	 * @return Coterminal angle
	 */
	private double getCoterminalAngle(double angle) {
		while(angle<0) {
			angle += TWO_PI;
		}
		while(angle>TWO_PI) {
			angle = angle % TWO_PI;
		}
		return angle;
	}

	/**
	 * Initializes image map
	 */
	private void initializeImages() {
		images= new HashMap<String, Image>();
		ResourceBundle imageFile = ResourceBundle.getBundle("resources.languages/TurtleImages");
		int index=0;
		for(String k:imageFile.keySet())
		{
			Image turtle = new Image("File:images/"+imageFile.getString(k));
			images.put(k,turtle);
			imageKey.put(k,(double)index);
		}
	}

	/**
	 * Clones turtle properties including lines, image, X and Y coordinates, heading, etc.
	 */
	public Turtle clone() {
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
