package pen;

import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public abstract class Pen {

	/**
	 * Boolean for whether or not pen is down
	 */
	protected boolean penBoolean;

	/**
	 * Color of the next line to be drawn
	 */
	protected Color penColor;

	protected double penWidth;

	/**
	 * Start X coordinate for the next line
	 */
	protected double xStartLineLocation;

	/**
	 * Start Y coordinate for the next line
	 */
	protected double yStartLineLocation;

	public Pen() {
		this.penBoolean = true;
		this.penColor = Color.BLACK;
	}

	/**
	 * 
	 * @param xAmount Amount pixel change in x direction
	 * @param yAmount Amount pixel change in y direction
	 */
	public abstract void drawLine(double xAmount, double yAmount);

	/**
	 * Clears all the lines that were drawn by the turtle
	 */
	public abstract void clearLines();

	/**
	 * 
	 * @return List of line objects
	 */
	public abstract List<Line> getLines();

	/**
	 * 
	 * @param color Color of the line
	 */
	public void setPenColor(Color color) {
		penColor = color;
	}

	public void setWidth(double width) {
		penWidth = width;
	}

	/**
	 * 
	 * @param bool True or false boolean
	 */
	public void togglePenUpOrDown(boolean bool) {
		penBoolean = bool;
	}

	/**
	 * Sets the start X and start Y coordinate for the next line to be drawn
	 */
	public void setStartLineLocation(double xCoord, double yCoord) {
		this.xStartLineLocation = xCoord;
		this.yStartLineLocation = yCoord;
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
	 * @return Color of the last line drawn
	 */
	public Color getPenColor() {
		return penColor;
	}
	
	/**
	 * 
	 * @return Width of the pen
	 */
	public double getPenWidth() {
		return penWidth;
	}

}
