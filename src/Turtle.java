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
//		this.imageURL = 
	}
	
	private void moveHorizontally(double amount) {
		xLocation += amount;
	}
	
	private void moveVertically(double amount) {
		yLocation += amount;
	}

	private void setXPosition(double xCoordinate) {
		xLocation = xCoordinate;
	}
	
	private void setYPosition(double yCoordinate) {
		yLocation = yCoordinate;
	}
	
	private void setAngle(double heading) {
		angle = heading;
	}
	
}
