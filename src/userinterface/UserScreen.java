package userinterface;

import backend.SLogoModel;
import backend.Variable;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import pen.Pen;
import resources.languages.Language;
import resources.languages.LanguageFactory;
import turtle.Turtle;

import java.util.*;

public class UserScreen extends Application
{
    private static final String DEFAULT_RESOURCES = "resources.languages/";
    private static final String TITLE = "Slogo";

    private static final int FRAMES_PER_SECOND = 10;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final int XSIZE = 800;
    private static final int YSIZE = 600;
    private boolean rave=false;

    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Scene myScene;
    private Stage myStage;

    private ResourceBundle descriptions;
    private ResourceBundle turtleImages;
    private ResourceBundle properties;
    private ResourceBundle colors;

    private ArrayList<Turtle> turtles = new ArrayList<Turtle>();
    private History history = new History();
    private HashMap<String, String> userCommands = new HashMap<String,String >();
    private Turtle myTurtle;
    private Pen pen;
    private SLogoModel mySLogoModel;
    private TextArea variables;
    private Button resetButton;
    private TextArea commands;
    private TextArea console;
    private Pane turtlePane;
    private Group root;
    private String language = "English";
    private List<Line> lines = new ArrayList<Line>();
    private Timeline animation;

    private final WebView browser = new WebView();
    private final WebEngine webEngine = browser.getEngine();


//INITIALIZATION RELATED FUNCTIONS
    //SCENE RELATED FUNCTIONS_________________________________________________________________________

        public UserScreen(Turtle t, Pen pen){
        		this.myTurtle =t;
        		this.pen = pen;
        	}

       /* Add slogomodel to the view
        */
        public void addSlogo(SLogoModel s){mySLogoModel = s; setupProperties(language);}

        /*will be used to insantiate all of the visual elements in
         * in the slogo project and add to the scene which returns to
         * start --this calls the menu related functions
         */
        public Scene setScene(int width, int length) {
             root = new Group();
            myScene = new Scene(root, width, length);

            setupProperties("English");

            VBox right = createSideMenu();
            HBox bottom = createBottomMenu();
            BorderPane form = new BorderPane();

            turtlePane = new Pane();
            turtlePane.setPrefHeight(500);
            turtlePane.setPrefWidth(500);

            turtles.add(myTurtle);
            turtlePane.getChildren().add(myTurtle);
//            for (Turtle turtle : turtles) {
//                turtlePane.getChildren().add(turtle);
//            }

            form.setRight(right);
            form.setBottom(bottom);
            form.setCenter(turtlePane);
            form.setPrefSize(XSIZE, YSIZE);

            root.getChildren().add(form);
            
            beginAnimationLoop();

            return myScene;
        }
        
        public void beginAnimationLoop() {
	    		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
	    				e -> step(SECOND_DELAY));
	    		animation = new Timeline();
	    		animation.setCycleCount(Timeline.INDEFINITE);
	    		animation.getKeyFrames().add(frame);
	    		animation.play();  
        }
        
        public void step(double elapsedTime) {
//        		System.out.println(myTurtle.getRotate());
            drawLine();

            if(rave)
            {
                int a = (int)(Math.random()*colors.keySet().size());
                int b=0;
                for(String s : colors.keySet()) {
                    if(b==a)
                        break;
                    changeBackground(s);
                    b++;
                }
            }
        }

        /* creates the scene within the stage by calling setScene
         * defines/ initializes the state and begins stepping
         */
        public void start(Stage stage) {
            myScene = setScene(XSIZE, YSIZE); // get the scene
            myStage=stage;
            stage.setResizable(false);
            stage.setScene(myScene);
            stage.setTitle(TITLE);
            stage.show();
        }

    //PROPERTY INIT FUNCTIONS_________________________________________________________________________

        /* initializes the properties files containing value
         * key pairs for commands, images, and colors
         * Also gives slogomodel the correct language
         */
        private void setupProperties(String lang) {
            turtleImages = ResourceBundle.getBundle(DEFAULT_RESOURCES + "TurtleImages");
            properties = ResourceBundle.getBundle(DEFAULT_RESOURCES + lang);
            colors = ResourceBundle.getBundle(DEFAULT_RESOURCES + "Colors");
            try {
                descriptions = ResourceBundle.getBundle(DEFAULT_RESOURCES + lang + "Descriptions");
            }
            catch(MissingResourceException m)
            {
                descriptions = ResourceBundle.getBundle(DEFAULT_RESOURCES + lang);
            }
            LanguageFactory backendLanguage = new LanguageFactory();
            Language myLanguage =backendLanguage.getLanguage(lang);
            if(mySLogoModel!=null)
                mySLogoModel.setLanguage(myLanguage);
            language=lang;
        }

//MENU RELATED FUNCTIONS
    //MENU INIT FUNCTIONS_________________________________________________________________________________________

        /* Defines the sidemenu which contains comboboxes
         * for language, line colro, background color
         * images-- text areas for commands and variables
         * and a reset button
         */
        private VBox createSideMenu() {
            VBox interactives = new VBox();

            resetButton = getResetButton();
            commands = getCommandsList();
            variables = getVariableList();
            ComboBox imageCombo = getImageCombo();
            ComboBox lineCombo = getLineCombo();
            ComboBox background = getBackgroundCombo();
            ComboBox language = getLanguageCombo();

            interactives.setPrefWidth(200);
            interactives.setStyle("-fx-background-color: #008000");
            interactives.setPadding(new Insets(20, 10, 20, 10));
            interactives.setAlignment(Pos.CENTER);
            interactives.setSpacing(10);
            interactives.getChildren().addAll(language, background, imageCombo,
                    lineCombo, commands, variables, resetButton, getSetCommand(),getHelpButton(),
                    getExtraHelpButton());

            return interactives;
        }

        /* Defines the location and creation of the bottom menu
         * which contains the console
         */
        private HBox createBottomMenu() {
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

    //COMMAND FUNCTIONS//__________________________________________________________________________________________

        /* Initializes the shape and properties of the command area
         */
        private TextArea getCommandsList() {
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
        private void setupCommandsList() {
            commands.appendText("Inherent Commands: \n\n");
            for (String cmd : descriptions.keySet()) {
                commands.appendText(cmd.toUpperCase() + "\n");
                commands.appendText( descriptions.getString(cmd) + "\n\n");
            }
            commands.appendText("User Defined Commands: \n\n");
        }

        /* Appends a previously run command to the history
         */
        private void addPreviousCommand(String s)
        {
            history.add(s);
        }

    //VARIABLE FUNCTIONS__________________________________________________________________________________________

        /* Creates properties of the variable textarea
         * and initialized the values from the vars hashmap
         */
        private TextArea getVariableList() {
            TextArea textArea = new TextArea();
            textArea.prefWidth(XSIZE / 7 * 4);
            textArea.setPrefWidth(XSIZE / 7 * 4);
            textArea.setPrefHeight(YSIZE / 7 * 2);
            textArea.setEditable(false);
            textArea.setWrapText(false);
            textArea.appendText("Current Variables: \n\n");

            return textArea;
        }

        /* adds the variables from controller to the hashmap
         * and rewrites the variables to the textarea
         */
        private void addVariable() {
            variables.clear();
            variables.appendText("Current Variables: \n");
            for(Variable var : mySLogoModel.getMyData().getMyVariables())
                variables.appendText(var.getName() + ":       " +var.getValue()+"\n\n");
        }

    //CONSOLE FUNCTIONS__________________________________________________________________________________________

        /* Defines the orientation and and onAction properties
         * for the text console and sends the reference to be
         * set to instance variable console
         */
        private TextArea getConsole() {
            console = new TextArea();
            console.prefWidth(XSIZE / 7 * 4);
            console.setPrefWidth(XSIZE);
            console.setPrefHeight(YSIZE);
            console.setEditable(true);
            console.setWrapText(true);
            console.setOnKeyPressed(e -> consoleHandler(e));
            console.setText("enter");
            console.positionCaret(1);
            return console;
        }

        /* Defines the actions to be taken
         *  when the user types in the console
         */
        private void consoleHandler( KeyEvent k) {

            if (k.getCode().equals(KeyCode.ENTER)) {
                k.consume();
                String textHolder=console.getText();
                if(userCommands.containsKey(textHolder))
                    mySLogoModel.parse(userCommands.get(textHolder));
                else
                    mySLogoModel.parse(textHolder);
                console.setText("");
                addVariable();
            }

            else if (k.getCode().equals(KeyCode.UP)) {
                this.displayPrev(console);
            }
            else if (k.getCode().equals(KeyCode.DOWN)) {
                this.displayNext(console);
            }
        }

        /* cycles forward through command list and
         * sets text value of console to next command
         */
        private void displayNext(TextArea console) {
            if(history.hasNext())
                console.setText(history.moveForward());

        }

        /* cycles back through command list and
         * sets text value of console to prev command
         */
        private void displayPrev(TextArea console) {
            if(history.hasPrev())
                console.setText(history.moveBack());
        }

    //LANGUAGE FUNCTIONS_________________________________________________________________________________________

        /* Defines the onAction of the language combo box
         * re-initializes the properties files and updates menu
         */
        private void handleLanguageCombo(String s) {
            setupProperties(s);
            setupCommandsList();
        }

        /* Initializes the location size and options
         * in the language combobox and sends reference
         * to be added to the side menu
         */
        private ComboBox getLanguageCombo() {
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
            combobox.setOnAction(e->handleLanguageCombo(combobox.getValue()));
            return combobox;
        }

    //BUTTON FUNCTIONS____________________________________________________________________________________________

        /* Defines the creation an onAction event
         * and returns reference to be set to instance
         * resetButton
         */
        private Button getResetButton() {
            Button b = new Button();
            ImageView rimage= new ImageView(new Image("File:images/reset.png"));
            rimage.setFitWidth(30);
            rimage.setFitHeight(30);
            b.setGraphic(rimage);
            b.setOnAction(e -> this.reset());
            return b;
        }

        private Button getSetCommand() {
            Button b = new Button("CMD");
            b.setOnAction(e -> importCMD());
            return b;
        }

        private Hyperlink getHelpButton() {
            Hyperlink h = new Hyperlink();
            h.setText("?");
            h.setTextFill(Color.BLACK);
            h.setOnAction(e->getHostServices().showDocument("https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php"));
            return h;
        }

        private Hyperlink getExtraHelpButton() {
            Hyperlink h = new Hyperlink();
            h.setText("!?");
            h.setTextFill(Color.BLACK);
            h.setOnAction(e->getHostServices().showDocument("https://www.lifeoptimizer.org/2010/05/27/being-a-better-you/"));
            return h;
        }

        /* Resets the turtle location, variables, draw pane
         * and redraws the UI
         */
        private void reset() {
            myTurtle.setToOrigin();
            myTurtle.setHeading(0);
            for(Line l: lines)
            {
                turtlePane.getChildren().remove(l);
            }
            lines.clear();
            myTurtle.clearLines();
        }


        private void importCMD() {
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(myStage);
            VBox dBox = new VBox(20);
            TextArea cmd = new TextArea();
            cmd.setPromptText("Enter your Command name");
            TextArea code = new TextArea();
            code.setPromptText("Enter code");
            Button enter = new Button ("enter");
            enter.setOnAction(e->{this.addCommand(cmd.getText(),code.getText()); dialog.close();});
            dBox.getChildren().addAll(cmd,code, enter);
            Scene dialogScene = new Scene(dBox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }

        private void addCommand(String cmd, String code)
        {
            if(!userCommands.containsKey(cmd)) {
                userCommands.put(cmd, code);
                commands.appendText(cmd + "\n\n");
            }
        }

    //TURTLE IMAGE FUNCTIONS_______________________________________________________________________________________

        /* Defines the combobox for turtle images and accesses the
         * properties table for images to set new images on the turtle
         */
        private ComboBox getImageCombo() {
            ObservableList<String> language =FXCollections.observableArrayList(new ArrayList<String>(turtleImages.keySet()));
            ComboBox<String> combobox = new ComboBox<>(language);
            combobox.setValue("Turtle");
            combobox.setPromptText("Turtle Image");
            combobox.setOnAction(e->myTurtle.setImage(combobox.getValue()));
            return combobox;
        }

    //COLOR SETTING FUNCTIONS_____________________________________________________________________________________

       /* Defines the location and color options for setting the color
        * of the lines the turtle draws and returns to VBox to add to menu
        */
        private ComboBox getLineCombo() {
            ObservableList<String> color =FXCollections.observableArrayList(new ArrayList<String>(colors.keySet()));
            ComboBox<String> combobox = new ComboBox<>(color);
            combobox.setValue("BLACK");
            combobox.setPromptText("LineColor");
            Color c = Color.web(colors.getString(combobox.getValue()));
            combobox.setOnAction(e->pen.setPenColor(Color.web(colors.getString(combobox.getValue()))));
            return combobox;
        }

        /* Defines the locatoin and color opotions for setting the color
         * of the background when the combobox is clicked
         */
        private ComboBox getBackgroundCombo() {
            ObservableList<String> options = FXCollections.observableList(new ArrayList<String>(colors.keySet()));
            options.add("Rave EPILEPSY WARNING");
            ComboBox<String> combobox = new ComboBox<>(options);
            combobox.setValue("WHITE");
            combobox.setPromptText("Background Color");
            combobox.setOnAction(e-> {rave=false; changeBackground(combobox.getValue());});
            return combobox;
        }

//VIEW RELATED FUNCTIONS
    //TURTLE FUNCTIONS____________________________________________________________________________________________

        /* Called by TurtleViewController to
         * make the turtle invisible on the form
         * in response to commands from backend
         */
        public void toggleTurtle(boolean t){myTurtle.setVisible(t);}

    //FORM FUNCTIONS____________________________________________________________________________________________

        /* Allows the controller to alter the
         * background of the UI calls changebackground
         */
        public void setBackgroundColor(Color c){

            //Figure out what this is
            if(colors.containsKey(c.toString()))
                changeBackground(c.toString());
        }

        /* Changes the background by accessung the properties table of colors
         * to decode the input value from the combobox
         */
        private void changeBackground(String value) {
           if(!value.equals("Rave EPILEPSY WARNING")) {
               turtlePane.setStyle("-fx-background-color: " + colors.getString(value));
           }
           else rave=true;
        }

        /* called to update the form to show the path
         * whenever the locatoun of turtle is changed
         */
        private void drawLine() {
            for(Line l:pen.getLines())
            {
                if(!turtlePane.getChildren().contains(l)){
                    turtlePane.getChildren().add(l);
                    lines.add(l);

                }
            }
        }

    //ERROR FUNCTIONS____________________________________________________________________________________________

       /* Creates an alert out of the error string sent by control
        * to inform the user of their error
        */
        public void printToScreen(String s){
            //look into getting an error type and error specific

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText(s);
            alert.showAndWait();
        }

//MISC GETTER FUNCTIONS

    /* Returns the properties file of the language being used to control
     */
    public ResourceBundle getLanguage()
    {
        return properties;
    }

    /* Returns XSize of turtle pane
     */
    public int getXSize(){return (int)turtlePane.getWidth();}

    /* Returns YSize of turtle pane
     */
    public int getYSize(){return (int)turtlePane.getHeight();}

    public SLogoModel getMyModel() {
        return mySLogoModel;
    }

}