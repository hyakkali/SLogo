package userinterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class SideMenu 
{
	
	private TilePane interactives = new TilePane();
	
	public SideMenu()
	{			 
			 interactives.setPadding(new Insets(5, 0, 5, 0));
			 interactives.setVgap(4);
			 interactives.setHgap(4);
			// interactives.setPrefColumns(2);
			 interactives.setPrefRows(3);
			 interactives.setStyle("-fx-background-color: DAE6F3;");
			 Button b1 = new Button("Button one");
			 Button b2 = new Button("Button two");
			 Button b3 = new Button("Button three");
			 Button b4 = new Button("Button four");
			 Button b5 = new Button("Button five");
			 interactives.getChildren().addAll(b1,b2,b3,b4,b5);
	}
	
	public TilePane getMenu()
	{
		return interactives;
	}
	
	

}
