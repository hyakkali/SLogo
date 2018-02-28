package main;

import backend.SLogoModel;
import javafx.application.Application;
import javafx.stage.Stage;
import turtle.Turtle;
import userinterface.UserScreen;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Turtle turtle = new Turtle();
        UserScreen view =  new UserScreen();
        Controller controller = new TurtleViewController(view, turtle);
        SLogoModel smodel = new SLogoModel(controller);
        view.start(primaryStage);
		view.addSlogo(smodel);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
