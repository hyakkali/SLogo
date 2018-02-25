import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Hemanth Yakkali
 * Turtle object that lies in the View part of the project.
 */
public class Turtle extends ImageView{
	
	private double xLocation;
	private double yLocation;
	private double angle;
	private String imageURL;
	
	public Turtle(){
		super(); //sets image found in url
		this.xLocation = 0;
		this.yLocation = 0;
		this.angle = 0;
//		this.imageURL; 
	}
	
	public void move(double angle, double amount) {
		xLocation += calculateXAmount(angle, amount);
		yLocation += calculateYAmount(angle, amount);
	}
	
	//setters
	public void setXPosition(double xCoordinate) {
		xLocation = xCoordinate;
	}
	
	public void setYPosition(double yCoordinate) {
		yLocation = yCoordinate;
	}
	
	public void setAngle(double heading) {
		angle = heading;
	}
	
	public void setImageURL(String url) {
		imageURL = url;
	}
		
	//getters
	public double getXLocation() {
		return xLocation;
	}
	
	public double getYLocation() {
		return yLocation;
	}
	
	public double getAngle() {
		return angle;
	}
	
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
