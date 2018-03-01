package pen;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.shape.Line;

public class LinePen extends Pen{
	
	/**
	 * List of lines drawn by the turtle
	 */
	public List<Line> lines;
	
	public LinePen() {
		this.lines = new ArrayList<>();	
	}
	
	public void drawLine(double xAmount, double yAmount) {
		if(penBoolean) {
			Line newLine = new Line(xStartLineLocation,yStartLineLocation,xStartLineLocation+xAmount,yStartLineLocation-yAmount);
			newLine.setStroke(penColor);
			lines.add(newLine);
		}
	}

	@Override
	public void clearLines() {
		lines.clear();
	}
	
	/**
	 * 
	 * @return List of line objects
	 */
	public List<Line> getLines() {
		return lines;
	}

}
