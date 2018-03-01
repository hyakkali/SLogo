package main;

import backend.SLogoModel;
import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import turtle.Turtle;
import userinterface.UserScreen;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		Turtle turtle = new Turtle();
        UserScreen view =  new UserScreen(turtle);
        Controller controller = new Controller(view, turtle);
        SLogoModel smodel = new SLogoModel(controller);
        view.start(primaryStage);
		view.addSlogo(smodel);
        primaryStage.setScene(view.setScene(800,600));
        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}

