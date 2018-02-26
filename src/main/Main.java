package main;


import javafx.application.Application;
import javafx.stage.Stage;
import turtle.Turtle;
import user_interface.UserScreen;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Turtle turtle = new Turtle();
		UserScreen view =  new UserScreen();
		Controller controller = new TurtleViewController(view, turtle);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
