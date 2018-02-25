package user_interface;

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
	
	public void moveHorizontally(double amount) {
		xLocation += amount;
	}
	
	public void moveVertically(double amount) {
		yLocation += amount;
	}

	public void setXPosition(double xCoordinate) {
		xLocation = xCoordinate;
	}

	public void setYPosition(double yCoordinate) {
		yLocation = yCoordinate;
	}

	public void setAngle(double heading) {
		angle = heading;
	}
	
}
