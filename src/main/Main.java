package main;

import backend.SLogoModel;
import javafx.application.Application;
import javafx.stage.Stage;
import turtle.Turtle;
import userinterface.UserScreen;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Turtle turtle = new Turtle();
		UserScreen view = new UserScreen();
		Controller controller = new TurtleViewController(view, turtle);
		SLogoModel model = new SLogoModel(controller);
	}
	
}
