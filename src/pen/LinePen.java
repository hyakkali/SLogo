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

	@Override
	public void drawLine(double xAmount, double yAmount) {
		if(penBoolean) {
			Line newLine = new Line(xStartLineLocation,yStartLineLocation,xStartLineLocation,yStartLineLocation);
			newLine.setStroke(penColor);
			newLine.setStrokeWidth(penWidth);
			lines.add(newLine);
		}
	}

	@Override
	public void clearLines() {
		lines.clear();
	}

	@Override
	public List<Line> getLines() {
		return lines;
	}

}
