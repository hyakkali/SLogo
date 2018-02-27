package user_interface;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import jdk.nashorn.internal.ir.annotations.Immutable;
import turtle.Turtle;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;


public class UserScreen extends Application {

    private static final String TITLE = "Slogo";
    private static final int XSIZE = 800;
    private static final int YSIZE = 600;

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Scene myScene;
    private Stage myStage;
    private static final String DEFAULT_RESOURCES = "resources.languages/";


    private ResourceBundle properties;
    private ResourceBundle descriptions;
    private ResourceBundle turtleImages;
    private ResourceBundle colors;

    private Turtle myTurtle = new Turtle();
    private ArrayList<Turtle> turtles = new ArrayList<Turtle>();
    private HashMap<String, Object> vars = new HashMap<String, Object>();
    private TextArea console;
    private TextArea commands;
    private TextArea variables;
    private Button resetButton;
    private ArrayList<Button> buttons;
    private History history = new History();
    private Pane turtlePane;


    /*will be used to draw the form on initialization
     * and hopefully recycled to redraw after the user
     * changes the shape of the window
     */
    private void drawForm(int width, int length) {

    }

    /*will be used to insantiate all of the visual elements in
     * in the slogo project and add to the scene
     */
    private Scene setScene(int width, int length)
    {
        Group root = new Group();
        myScene = new Scene(root, width, length);

        setupProperties("English");

        VBox right = createSideMenu();
        HBox bottom = createBottomMenu();
        BorderPane form = new BorderPane();

        turtlePane = new Pane();
        turtles.add(myTurtle);
        for (Turtle turtle : turtles) {
            turtlePane.getChildren().add(turtle);
        }

        form.setRight(right);
        form.setBottom(bottom);
        form.setCenter(turtlePane);
        form.setPrefSize(XSIZE, YSIZE);

        root.getChildren().add(form);

        return myScene;
    }

    private void reset() {
        printToScreen("you done fucked up");
    }


    /*creates the scene within the stage by calling setScene
     * defines the keys necessary for the project
     * initializes the state and begins stepping
     */
    @Override
    public void start(Stage stage) {
        myScene = setScene(XSIZE, YSIZE); // get the scene
        myStage=stage;

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
        turtleImages = ResourceBundle.getBundle(DEFAULT_RESOURCES + "TurtleImages");
        properties = ResourceBundle.getBundle(DEFAULT_RESOURCES + language);
        colors = ResourceBundle.getBundle(DEFAULT_RESOURCES + "Colors");
        try {
            descriptions = ResourceBundle.getBundle(DEFAULT_RESOURCES + language + "Descriptions");
        }
        catch(MissingResourceException m)
        {
            descriptions = ResourceBundle.getBundle(DEFAULT_RESOURCES + language);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    /* called to update the turtle location
     * whenever the stuff is changed
     */
    private void step(double elapsedTime) {
    }

    private void drawLine()
    {
        Line toDraw = myTurtle.getLastLine();
        turtlePane.getChildren().add(toDraw);
    }


    private VBox createSideMenu() {
        VBox interactives = new VBox();
        resetButton = getResetButton();
        commands = getCommandsList();
        ComboBox imageCombo = getImageCombo();
        ComboBox lineCombo = getLineCombo();
        variables = getVariableList();
        ComboBox background = getBackgroundCombo();
        ComboBox language = getLanguageCombo();
        interactives.setPrefWidth(200);
        interactives.setStyle("-fx-background-color: #008000");
        interactives.setPadding(new Insets(20, 10, 20, 10));
        interactives.setAlignment(Pos.CENTER);
        interactives.setSpacing(10);
        interactives.getChildren().addAll(language, background, imageCombo, lineCombo, commands, variables, resetButton);
        return interactives;
    }

    private ComboBox getBackgroundCombo()
    {
        ObservableList<String> options = FXCollections.observableList(new ArrayList<String>(colors.keySet()));
        ComboBox<String> combobox = new ComboBox<>(options);
        combobox.setValue("WHITE");
        combobox.setPromptText("Background Color");

        combobox.setOnAction(e->changeBackground(combobox.getValue()));
        return combobox;
    }

    private ComboBox getLanguageCombo()
    {
        ObservableList<String> language =FXCollections.observableArrayList(
                "English",
                "Chinese",
                "French",
                "German",
                "Italian",
                "Portuguese",
                "Russian",
                "Spanish"
        );
        ComboBox<String> combobox = new ComboBox<>(language);
        combobox.setValue("English");
        combobox.setPromptText("Language");
        combobox.setOnAction(e->handleLanguageCombo(combobox.getValue()));
        return combobox;
    }

    private ComboBox getImageCombo()
    {
        ObservableList<String> language =FXCollections.observableArrayList(new ArrayList<String>(turtleImages.keySet()));
        ComboBox<String> combobox = new ComboBox<>(language);
        combobox.setValue("Turtle");
        combobox.setPromptText("Turtle Image");
        combobox.setOnAction(e->myTurtle.setImage(combobox.getValue()));
        return combobox;
    }

    private ComboBox getLineCombo()
    {
        ObservableList<String> color =FXCollections.observableArrayList(new ArrayList<String>(colors.keySet()));
        ComboBox<String> combobox = new ComboBox<>(color);
        combobox.setValue("BLACK");
        combobox.setPromptText("LineColor");
        Color c = Color.web(colors.getString(combobox.getValue()));
        combobox.setOnAction(e->myTurtle.setPenColor(c));
        return combobox;
    }

    public ResourceBundle getLanguage()
    {
        return properties;
    }

    private void handleLanguageCombo(String s)
    {
        setupProperties(s);
        setupCommandsList();
        resetButton.setText(properties.getString("Reset"));
    }


    private void changeBackground(String v)
    {
        turtlePane.setStyle("-fx-background-color: " +colors.getString(v));
    }

    private HBox createBottomMenu()
    {
        HBox interactives = new HBox();
        console = getConsole();
        interactives.setPrefHeight(YSIZE / 9 * 2);
        interactives.setStyle("-fx-background-color: #008000");
        interactives.setPadding(new Insets(20, 10, 20, 10));
        interactives.setAlignment(Pos.TOP_LEFT);
        interactives.setSpacing(10);
        interactives.getChildren().addAll(console);
        return interactives;
    }

    public void displayError(String s)
    {
        console.clear();
        console.setText("Error: "+s);
    }

    private Button getResetButton() {
        Button b = new Button(descriptions.getString("Reset"));
        b.setOnAction(e -> this.reset());
        return b;
    }




//MENU RELATED METHODS//

   //COMMAND FUNCTIONS//__________________________________________________________________________________________
        /* Initializes the shape and properties of the command area
         */
       private TextArea getCommandsList()
       {
       commands = new TextArea();
       commands.prefWidth(XSIZE / 7 * 4);
       commands.setPrefWidth(XSIZE / 7 * 4);
       commands.setPrefHeight(YSIZE / 7 * 2);
       commands.setEditable(false);
       commands.setWrapText(true);
       setupCommandsList();

       return commands;
   }

        /*Initializes the command window with the descriptions
         * of each command from the properties/ user definition
         */
        private void setupCommandsList()
        {
            commands.appendText("Inherent Commands: \n\n");
            for (String cmd : descriptions.keySet()) {
                commands.appendText(cmd + "\n");
                commands.appendText(descriptions.getString(cmd) + properties.getString(cmd) + "\n\n");
            }
            commands.appendText("User Defined Commands: \n\n");
        }

        /* Appends user defined command to the list of callable commands
         *
         */
        public void addCommand(String s)
        {
            commands.appendText(s+"\n\n");
        }

    //____________________________________________________________________________________________________________


    //VARIABLE FUNCTIONS__________________________________________________________________________________________

        /* Creates properties of the variable textarea
         * and initialized the values from the vars hashmap
         */
        private TextArea getVariableList()
        {
        TextArea textArea = new TextArea();
        textArea.prefWidth(XSIZE / 7 * 4);
        textArea.setPrefWidth(XSIZE / 7 * 4);
        textArea.setPrefHeight(YSIZE / 7 * 2);
        textArea.setEditable(false);
        textArea.setWrapText(false);
        textArea.appendText("Current Variables: \n\n");
        for (String var : vars.keySet()) {
            textArea.appendText(var + ": " + vars.get(var).toString() + "\n\n");
        }
        return textArea;
    }

        /* adds the variables from controller to the hashmap
         *  and rewrites the variables to the textarea
         */
        public void addVariable(String s, Object o)
        {
            variables.clear();
            vars.put(s,o);
            for(String var : vars.keySet())
                variables.appendText(var + ": " +vars.get(var).toString()+"\n\n");
        }
    //____________________________________________________________________________________________________________

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
            //slogoModel.parse(console.getText());
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
    public void addVariable(String s)
    {
        //Figure out how this will work
    }
    public void toggleTurtle(boolean t){myTurtle.setVisible(t);}
    public void addPreviousCommand(String command){history.add(command);}

    public void printToScreen(String s){
        //look into getting an error type and error specific
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText(s);
        alert.showAndWait();

    }


    public void setBackgroundColor(Color c){

        //Figure out what this is
        if(colors.containsKey(c.toString()))
         changeBackground(c.toString());
    }



    private void displayPrev(TextArea console)
    {
        if(history.hasPrev())
            console.setText(history.moveBack());
    }
}