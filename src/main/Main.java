package main;


import javafx.application.Application;
import javafx.stage.Stage;
import turtle.Turtle;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Turtle turtle = new Turtle();
		UserInterface view =  new UserInterface();
		Controller controller = new TurtleViewController(view, turtle);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
