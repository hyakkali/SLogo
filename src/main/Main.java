package main;

import backend.SLogoModel;
import javafx.application.Application;
import javafx.stage.Stage;
import turtle.Turtle;
import user_interface.UserScreen;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Turtle turtle = new Turtle();
		UserScreen view = new UserScreen();
		Controller controller = new Controller(view, turtle);
		SLogoModel model = new SLogoModel(controller);
	}
	
}
