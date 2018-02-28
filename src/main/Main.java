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
        Controller controller = new Controller(view, turtle);
        SLogoModel smodel = new SLogoModel(controller);
        view.addSlogo(smodel);
        view.start(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}

