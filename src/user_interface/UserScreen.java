package user_interface;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;


public class UserScreen extends Application {

    private static final String TITLE = "Slogo";
    private static final int XSIZE = 800;
    private static final int YSIZE = 600;

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    private static final Paint BACKGROUND = Color.AZURE;
    private static final String[] languages = {"English.properties"};

    private ResourceBundle resources;
    private Scene myScene;
    private static final String DEFAULT_RESOURCES = "resources.languages/";


    private ResourceBundle properties;
    private ResourceBundle descriptions;

    private ArrayList<Turtle> turtles = new ArrayList<Turtle>();
    private Turtle t = new Turtle();
    private HashMap<String, Object> vars = new HashMap<String, Object>();
    private TextArea console;
    private History history= new History();
//    public UserSceen(List<Turtle> t)
//    {
//        turtles=(ArrayList<Turtle>)t;
//    }

    /*will be used to draw the form on initialization
     * and hopefully recycled to redraw after the user
     * changes the shape of the window
     */
    private void drawForm(int width, int length) {

    }

    /*will be used to insantiate all of the visual elements in
     * in the slogo project and add to the scene
     */
    private Scene setScene(int width, int length) {
        turtles.add(t);
        setupProperties("English");
        Group root = new Group();
        myScene = new Scene(root, width, length);
        setScreen(root);
        return myScene;
    }


    private void setScreen(Group r) {

        BorderPane form = new BorderPane();

        VBox right = createSideMenu();
        HBox bottom = createBottomMenu();
        Pane p = new Pane();
        for (Turtle turtle : turtles) {
            p.getChildren().add(turtle);
        }

        form.setRight(right);
        form.setBottom(bottom);
        form.setPrefSize(XSIZE, YSIZE);
        form.setCenter(p);
        r.getChildren().add(form);
    }

    private void reset() {
        System.out.print("Reset");
    }


    /*creates the scene within the stage by calling setScene
     * defines the keys necessary for the project
     * initializes the state and begins stepping
     */
    @Override
    public void start(Stage stage) {
        myScene = setScene(XSIZE, YSIZE); // get the scene


        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        //attach "game loop" to timeline to play it
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private void setupProperties(String language) {
        properties = ResourceBundle.getBundle(DEFAULT_RESOURCES + language);
        descriptions = ResourceBundle.getBundle(DEFAULT_RESOURCES + language + "_Descriptions");

    }

    public static void main(String[] args) {
        launch(args);
    }

    /* called to update the turtle location
     * whenever the stuff is changed
     */
    private void step(double elapsedTime) {
    }


    private VBox createSideMenu() {
        VBox interactives = new VBox();
        Button resetButton = getResetButton();
        TextArea commands = getCommandsList();
        TextArea variables = getVariableList();
//        Button loadButton = getLoadButton();
//        Button saveButton = getSaveButton();
        interactives.setPrefWidth(200);
        interactives.setStyle("-fx-background-color: #008000");
        interactives.setPadding(new Insets(20, 10, 20, 10));
        interactives.setAlignment(Pos.CENTER);
        interactives.setSpacing(10);
        interactives.getChildren().addAll(commands, variables, resetButton);
        return interactives;
    }

    private HBox createBottomMenu() {
        HBox interactives = new HBox();

        TextArea console = getConsole();

        interactives.setPrefHeight(YSIZE / 9 * 2);
        interactives.setStyle("-fx-background-color: #008000");
        interactives.setPadding(new Insets(20, 10, 20, 10));
        interactives.setAlignment(Pos.TOP_LEFT);
        interactives.setSpacing(10);
        interactives.getChildren().addAll(console);
        return interactives;
    }

    public void addVariable(String s, Object o)
    {
        vars.put(s,o);
    }

    public void displayError(String s)
    {


    }

    private Button getResetButton() {

        Button b = new Button(properties.getString("Reset"));
        b.setOnAction(e -> this.reset());
        return b;
    }

    private TextArea getCommandsList() {
        TextArea commands = new TextArea();
        commands.prefWidth(XSIZE / 7 * 4);
        commands.setPrefWidth(XSIZE / 7 * 4);
        commands.setPrefHeight(YSIZE / 7 * 2);
        commands.setEditable(false);
        commands.setWrapText(true);

        for (String cmd : descriptions.keySet()) {
            commands.appendText(cmd + "\n");
            commands.appendText(descriptions.getString(cmd) + properties.getString(cmd) + "\n\n");
        }

        return commands;
    }

    private TextArea getVariableList() {
        TextArea textArea = new TextArea();
        textArea.prefWidth(XSIZE / 7 * 4);
        textArea.setPrefWidth(XSIZE / 7 * 4);
        textArea.setPrefHeight(YSIZE / 7 * 2);
        textArea.setEditable(false);
        textArea.setWrapText(false);

        for (String var : vars.keySet()) {
            textArea.appendText(var + ": " + vars.get(var).toString() + "\n\n");
        }

        return textArea;

    }

    private TextArea getConsole() {
        TextArea console = new TextArea();
        console.prefWidth(XSIZE / 7 * 4);
        console.setPrefWidth(XSIZE);
        console.setPrefHeight(YSIZE);
        console.setEditable(true);
        console.setWrapText(true);
        console.setOnKeyPressed(e -> consoleHandler(e.getCode()));
        return console;
    }

    private void consoleHandler( KeyCode k) {
        if (k.equals(KeyCode.ENTER)) {
            //send to parser
        }
        if (k.equals(KeyCode.UP)) {
            this.displayPrev(console);
        }
        if (k.equals(KeyCode.DOWN)) {
            this.displayNext(console);
        }
    }

    private void displayNext(TextArea console)
    {
        if(history.hasNext())
            console.setText(history.moveForward());
    }

    private void displayPrev(TextArea console)
    {
        if(history.hasPrev())
            console.setText(history.moveBack());
    }
}