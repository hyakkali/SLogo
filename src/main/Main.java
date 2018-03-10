package main;

import java.util.ArrayList;

import backend.SLogoModel;
import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import pen.LinePen;
import turtle.Turtle;
import userinterface.UserScreen;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ArrayList<Turtle> turtles = new ArrayList<>();
		for(int i=0;i<3;i++) { //3 for testing purposes
			turtles.add(new Turtle(new LinePen(),i+1));
		}
        UserScreen view =  new UserScreen(turtles, primaryStage, e->getNewForm());
        Controller controller = new Controller(view, turtles);
        SLogoModel smodel = new SLogoModel(controller);
        controller.initSlogo(smodel);
		view.initializeBackend(smodel);
	}
	public void getNewForm()
	{
		Stage secondaryStage = new Stage();
		ArrayList<Turtle> turtles = new ArrayList<>();
		for(int i=0;i<3;i++) { //3 for testing purposes
			turtles.add(new Turtle(new LinePen(),i+1));
		}
		UserScreen view =  new UserScreen(turtles, secondaryStage, e->getNewForm());
		Controller controller = new Controller(view, turtles);
		SLogoModel smodel = new SLogoModel(controller);
		controller.initSlogo(smodel);
		view.initializeBackend(smodel);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
