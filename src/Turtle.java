import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Hemanth Yakkali
 * Turtle object that lies in the View part of the project.
 */
public class Turtle extends ImageView{
	
	/**
	 * X coordinate of the turtle
	 */
	private double xLocation;
	
	/**
	 * Y coordinate of the turtle
	 */
	private double yLocation;
	
	/**
	 * Current heading of the turtle
	 */
	private double heading;
	
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
		this.xLocation = 0;
		this.yLocation = 0;
		this.heading = 0;
		this.setImage(turtleImage);
	}
	
	/**
	 * 
	 * @param angle Heading of the turtle
	 * @param amount Amount of pixels to move
	 */
	public void move(double angle, double amount) {
		this.xLocation += calculateXAmount(angle, amount);
		this.yLocation += calculateYAmount(angle, amount);
	}
	
	/**
	 * 
	 * @param xCoordinate X coordinate of the turtle
	 */
	//setters
	public void setXPosition(double xCoordinate) {
		this.xLocation = xCoordinate;
	}
	
	/**
	 * 
	 * @param yCoordinate Y coordinate of the turtle
	 */
	public void setYPosition(double yCoordinate) {
		this.yLocation = yCoordinate;
	}
	
	/**
	 * 
	 * @param heading Heading of the turtle
	 */
	public void setAngle(double heading) {
		this.heading = heading;
	}
	
	/**
	 * 
	 * @param url String path to an image
	 */
	public void setImage(String url) {
		imageURL = url;
		this.setImage(turtleImage);
	}
	
	//getters
	/**
	 * 
	 * @return X coordinate of the turtle
	 */
	public double getXLocation() {
		return xLocation;
	}
	
	/**
	 * 
	 * @return Y coordinate of the turtle
	 */
	public double getYLocation() {
		return yLocation;
	}
	
	/**
	 * 
	 * @return Current heading of the turtle
	 */
	public double getAngle() {
		return heading;
	}
	
	/**
	 * 
	 * @return Current path to the image file being used 
	 */
	public String getImageURL() {
		return imageURL;
	}
	
	//misc
	private double calculateXAmount(double angle, double amount) {
		return amount*Math.cos(Math.toRadians(angle));
	}
	
	private double calculateYAmount(double angle, double amount) {
		return amount*Math.sin(Math.toRadians(angle));
	}
	
}
