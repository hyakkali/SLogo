package userinterface;

import backend.SLogoModel;
import backend.Variable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import resources.languages.Language;
import resources.languages.LanguageFactory;
import turtle.Turtle;

import java.awt.*;
import java.util.*;
import java.util.List;

public class UserScreen extends Application {
    private static final String DEFAULT_RESOURCES = "resources.languages/";
    private static final String helpURL = "https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php";
    private static final String TITLE = "Slogo";

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final int XSIZE = 800;
    private static final int YSIZE = 600;

    private final double TURTLE_MOVE = 20.0;
    private final double PEN_THICKNESS = 0.5;

    private final double ACTIVE_TURTLE = 0.0;
    private final double INACTIVE_TURTLE = 0.5;

    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Scene myScene;


    private ResourceBundle descriptions;
    private ResourceBundle turtleImages;
    private ResourceBundle properties;
    private ResourceBundle colors;
    private VariableList variables;
    private ArrayList<Turtle> turtles;      public ArrayList<Turtle> activeTurtles = new ArrayList<>();
    private HashMap<Integer, String> colorMap = new HashMap<>();
    private HashMap<Integer, String> imageMap = new HashMap<>();

   // private HashMap<String, String> userCommands = new HashMap<>();
    //private HashMap<Variable, Turtle> varsList = new HashMap<>();
    private SLogoModel mySLogoModel;
    private TextArea commands;
    private Pane turtlePane;
    private String language = "English";
    private List<Line> lines = new ArrayList<>();
    private String[] languageOptions = {"English", "Chinese", "French", "German", "Italian", "Portuguese", "Russian", "Spanish"};
    private Stack<State> history = new Stack<>();



//INITIALIZATION RELATED FUNCTIONS
    //SCENE RELATED FUNCTIONS_________________________________________________________________________

       /* adds the turtles called for by controller to the userscreen
        */
        public UserScreen(ArrayList<Turtle> t){
        		this.turtles = t;
        	}

       /* Add slogomodel to the view
        */
        public void addSlogo(SLogoModel s){mySLogoModel = s; setupProperties(language);}

    /*will be used to insantiate all of the visual elements in
     * in the slogo project and add to the scene which returns to
     * start --this calls the menu related functions
     */
    public Scene setScene(int width, int length) {
        Group root = new Group();
        myScene = new Scene(root, width, length);
        myScene.addEventFilter(MouseEvent.MOUSE_CLICKED, e-> saveState());
        setupProperties("English");
        myScene.addEventFilter(KeyEvent.KEY_PRESSED,e->{if(e.getCode().equals(KeyCode.ALT)) loadState();});
        VBox right = createSideMenu();
        HBox bottom = createBottomMenu();
        BorderPane form = new BorderPane();

        turtlePane = new Pane();
        turtlePane.setPrefHeight(500);
        turtlePane.setPrefWidth(500);
        turtlePane.setStyle("-fx-background-color: #ffffff");
        turtleSetup();

        form.setRight(right);
        form.setBottom(bottom);
        form.setCenter(turtlePane);
        form.setPrefSize(XSIZE, YSIZE);

        root.getChildren().add(form);

        beginAnimationLoop();

        return myScene;
    }

    private void turtleSetup()
    {
        for (Turtle turtle : turtles) {
            if(turtle.getActive())
                activeTurtles.add(turtle);

            turtle.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    MouseButton button = event.getButton();
                    if (button == MouseButton.PRIMARY) {
                        if (event.getClickCount() == 2) {
                            if (activeTurtles.contains(turtle)) {
                                activeTurtles.remove(turtle);
                                turtle.setActive(false);
                                turtle.setEffect(changeImageBrightness(INACTIVE_TURTLE));
                            } else {
                                activeTurtles.add(turtle);
                                turtle.setActive(true);
                                turtle.setEffect(changeImageBrightness(ACTIVE_TURTLE));
                            }
                        } else if (event.getClickCount() == 1) {
                            turtle.requestFocus();
                        }
                        //add set active or inactive
                    } else if (button == MouseButton.SECONDARY) {
                        ObservableList<MenuItem> menu = createContextMenuList(turtle);
                        ContextMenu cMenu = MenuBuilder.buildContext(menu);
                        cMenu.show(turtle, turtle.getX() + 150, turtle.getY() + 100);
                    }
                }

            });

            turtle.setOnKeyPressed(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent event) {
                    saveState();
                    if (turtle.getActive()) {
                        if (event.getCode() == KeyCode.D) {
                            turtle.setRotate(turtle.getRotate() + TURTLE_MOVE);
                        } else if (event.getCode() == KeyCode.A) {
                            turtle.setRotate(turtle.getRotate() - TURTLE_MOVE);
                        } else if (event.getCode() == KeyCode.W) {
                            turtle.move(turtle.getRotate(), TURTLE_MOVE);
                        } else if (event.getCode() == KeyCode.S) {
                            turtle.move(turtle.getRotate(), -1 * TURTLE_MOVE);
                        } else if (event.getCode() == KeyCode.D) {
                            turtle.pen.togglePenUpOrDown(true);
                        } else if (event.getCode() == KeyCode.U) {
                            turtle.pen.togglePenUpOrDown(false);
                        } else if (event.getCode() == KeyCode.Y) {
                            turtle.pen.setPenWidth(turtle.pen.getPenWidth() - PEN_THICKNESS);
                        } else if (event.getCode() == KeyCode.T) {
                            turtle.pen.setPenWidth(turtle.pen.getPenWidth() + PEN_THICKNESS);
                        }
                    }
                }
            });
            turtlePane.getChildren().add(turtle);
        }
    }


    /* animated the screen
     */
    private void beginAnimationLoop() {

        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private void step(double elapsedTime) {
        for (Turtle turtle : activeTurtles) {
            drawLine(turtle);
        }
    }

    /*  changes the brightness of the turtle

     */
    private ColorAdjust changeImageBrightness(double value) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(value);
        return colorAdjust;
    }



    /* creates the scene within the stage by calling setScene
     * defines/ initializes the state and begins stepping
     */
    public void start(Stage stage) {
        myScene = setScene(XSIZE, YSIZE); // get the scene
        stage.setResizable(false);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
    }


    /* initializes the properties files containing value
     * key pairs for commands, images, and colors
     * Also gives slogomodel the correct language
     */
    private void setupProperties(String lang) {
        turtleImages = ResourceBundle.getBundle(DEFAULT_RESOURCES + "TurtleImages");
        properties = ResourceBundle.getBundle(DEFAULT_RESOURCES + lang);
        colors = ResourceBundle.getBundle(DEFAULT_RESOURCES + "Colors");
        int index = 0;
        for (String color : colors.keySet()) {
            colorMap.put(index, color);
            index++;
        }
        index = 0;
        for (String color : turtleImages.keySet()) {
            imageMap.put(index, color);
            index++;
        }
        try {
            descriptions = ResourceBundle.getBundle(DEFAULT_RESOURCES + lang + "Descriptions");
        } catch (MissingResourceException m) {
            descriptions = ResourceBundle.getBundle(DEFAULT_RESOURCES + lang);
        }
        LanguageFactory backendLanguage = new LanguageFactory();
        Language myLanguage = backendLanguage.getLanguage(lang);
        if (mySLogoModel != null)
            mySLogoModel.setLanguage(myLanguage);
        language = lang;
    }


    /* Defines the sidemenu which contains comboboxes
     * for language, line colro, background color
     * images-- text areas for commands and variables
     * and a reset button
     */
    private VBox createSideMenu() {

        VBox interactives = new VBox();

        Button resetButton = MenuBuilder.buildButton("File:images/reset.png", e -> reset());
        Button helpButton = MenuBuilder.buildButton("File:images/help.png", e -> getHostServices().showDocument(helpURL));
        Button load = MenuBuilder.buildButton("Load", e->loadState());
        HBox buttons = new HBox(resetButton, helpButton, load);
        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);

        commands = getCommandsList();
        variables = new VariableList(XSIZE, YSIZE);
        ScrollPane scroll = new ScrollPane(variables);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        ObservableList<String> images = FXCollections.observableArrayList(new ArrayList<String>(turtleImages.keySet()));
        ObservableList<String> languages = FXCollections.observableArrayList(languageOptions);

        ComboBox imageCombo = MenuBuilder.buildCombo("Turtle Image", images, e -> setTurtleImage(e));
        ComboBox lineCombo = MenuBuilder.buildCombo("Line Color", colors, e -> setPenColor(e));
        ComboBox background = MenuBuilder.buildCombo("Background Color", colors, e -> setBackgroundColor(e));
        ComboBox language = MenuBuilder.buildCombo("Language", languages, e -> changeLanguage(e));

        interactives.setPrefWidth(200);
        interactives.setStyle("-fx-background-color: #008000");
        interactives.setPadding(new Insets(20, 10, 20, 10));
        interactives.setAlignment(Pos.CENTER);
        interactives.setSpacing(10);
        interactives.getChildren().addAll(language, background, imageCombo,
                lineCombo, commands, scroll, buttons);

        return interactives;
    }

    /* Defines the location and creation of the bottom menu
     * which contains the console
     */
    private HBox createBottomMenu() {
        HBox interactives = new HBox();
        Console console = new Console(XSIZE, YSIZE, i -> parse(i));
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
            commands.appendText(descriptions.getString(cmd) + "\n\n");
        }
        commands.appendText("User Defined Commands: \n\n");
    }

    /* Appends a previously run command to the history
     */
    public void addPreviousCommand(String s) {
        //useablecmds.add(s);
    }



    private ObservableList<MenuItem> createContextMenuList(Turtle turtle) {
        ObservableList<MenuItem> menu = FXCollections.observableArrayList();
        MenuItem mItem0 = new MenuItem("ID: " + Double.toString(turtle.getID()));
        MenuItem mItem1 = new MenuItem("X: " + Double.toString(turtle.getX()));
        MenuItem mItem2 = new MenuItem("Y: " + Double.toString(turtle.getY()));
        MenuItem mItem3 = new MenuItem("Heading: " + Double.toString(turtle.getRotate() % 360.0));
        Menu mItem4 = new Menu("Set Color");
        for (String color : colors.keySet()) {
            MenuItem colorOption = new MenuItem(color);
            colorOption.setOnAction(k -> turtle.pen.setPenColor(Color.valueOf(color)));
            mItem4.getItems().add(colorOption);
        }
        MenuItem mItem5 = new MenuItem("Pen Down: " + turtle.pen.getPenBoolean());
        MenuItem mItem6 = new MenuItem("Thickness: " + turtle.pen.getPenWidth());
        Menu mItem8 = new Menu("Set Image");
        for (String image : turtleImages.keySet()) {
            MenuItem colorOption = new MenuItem(image);
            colorOption.setOnAction(k -> turtle.setImage(image));
            mItem8.getItems().add(colorOption);
        }

        MenuItem mItem7 = new MenuItem("Active: " + turtle.getActive());
        menu.addAll(mItem0, mItem1, mItem2, mItem3, mItem4, mItem5, mItem6, mItem7, mItem8);
        return menu;
    }


    /* Allows the controller to alter the+
     * background of the UI calls changebackground
     */
    public void setBackgroundColor(Color c) {

        //Figure out what this is
        if (colors.containsKey(c.toString()))
            setBackgroundColor(c.toString());
    }


    /* called to update the form to show the path
     * whenever the locatoun of turtle is changed
     */
    private void drawLine(Turtle turtle) {
        for (Line l : turtle.pen.getLines()) {
            if (!turtlePane.getChildren().contains(l)) {
                turtlePane.getChildren().add(l);
                lines.add(l);
            }
        }
    }


    /* Creates an alert out of the error string sent by control
     * to inform the user of their error
     */
    public void printToScreen(String s) {
        //look into getting an error type and error specific

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText(s);
        alert.showAndWait();
    }

    public SLogoModel getMyModel() {
        return mySLogoModel;
    }

    public void setPenColor(double d) {
        int index = (int) d;
        setPenColor(colorMap.get(index));
    }

    public void setBackGroundColor(double d) {
        int index = (int) d;
    }

    public void setTurtleImage(double d) {
        int index = (int) d;
        setTurtleImage(imageMap.get(index));
    }

    public void setLanguage(double d) {
        int index = (int) d;
        changeLanguage(languageOptions[index]);
    }


    /* Resets the turtle location, variables, draw pane
     * and redraws the UI
     */
    private void reset() {
        for (Turtle t : turtles) {
            t.setToOrigin();
            t.setHeading(0);
        }
        clearLines();
    }
    
    public void clearLines() {
    		for(Turtle turtle:turtles) {
    			turtle.pen.clearLines();
    		}
    		for(Line l:lines) {
    			turtlePane.getChildren().remove(l);
    		}
    		lines.clear();
    }

    /* Defines the actions to be taken
     *  when the user types in the console
     */
    private void parse(String command) {
        variables.addVariables(mySLogoModel.getMyData().getMyVariables());
        mySLogoModel.parse(command);
        saveState();

    }

    /* save the state of the current of the screen
     */
    private void saveState() {

       State toAdd = new State(turtles, lines, turtlePane.getStyle(), language);
       if(history.isEmpty() || !toAdd.equals(history.peek())){
           history.push(toAdd);
            System.out.println("Add to stack");
       }
    }

    /* reinitialize the state from a past state 'undo'
     */
    private void loadState() {
       if(!history.isEmpty()) {
           State load = history.pop();
           turtles.clear();
           activeTurtles.clear();
           turtlePane.getChildren().clear();
           turtles.addAll(load.onScreen);
           turtleSetup();
           lines.clear();
           lines.addAll(load.drawnLines);
           setBackgroundColor(load.background);
           turtlePane.getChildren().addAll(lines);
       }
    }

    private void setPenColor(String color) {
        for(Turtle turtle: turtles) {
            turtle.pen.setPenColor(Color.web(color));
        }
    }

    /* Defines the onAction of the language combo box
     * re-initializes the properties files and updates menu
     */
    private void changeLanguage(String s) {
        setupProperties(s);
        setupCommandsList();
    }

    /* Changes the background by accessung the properties table of colors
     * to decode the input value from the combobox
     */
    private void setBackgroundColor(String value) {
        if(value.contains("-fx-background-color:"))
            turtlePane.setStyle(value);
        else
            turtlePane.setStyle("-fx-background-color: " + value);
    }

    private void setTurtleImage(String image) {
    		for(Turtle turtle:turtles) {
    	        turtle.setImage(image);
    		}
    }











    //TO BE UNUSED
    //BUTTON FUNCTIONS____________________________________________________________________________________________

//    /* Defines the creation an onAction event
//     * and returns reference to be set to instance
//     * resetButton
//     */
//
//
//    private Button getSetCommand() {
//        Button b = new Button("CMD");
//        b.setOnAction(e -> importCMD());
//        return b;
//    }
//
//
//
//    private Hyperlink getExtraHelpButton() {
//        Hyperlink h = new Hyperlink();
//        h.setText("!?");
//        h.setTextFill(Color.BLACK);
//        h.setOnAction(e->getHostServices().showDocument("https://www.lifeoptimizer.org/2010/05/27/being-a-better-you/"));
//        return h;
//    }
//
//    private void importCMD() {
//        final Stage dialog = new Stage();
//        dialog.initModality(Modality.APPLICATION_MODAL);
//        dialog.initOwner(myStage);
//        VBox dBox = new VBox(20);
//        TextArea cmd = new TextArea();
//        cmd.setPromptText("Enter your Command name");
//        TextArea code = new TextArea();
//        code.setPromptText("Enter code");
//        Button enter = new Button ("enter");
//        enter.setOnAction(e->{this.addCommand(cmd.getText(),code.getText()); dialog.close();});
//        dBox.getChildren().addAll(cmd,code, enter);
//        Scene dialogScene = new Scene(dBox, 300, 200);
//        dialog.setScene(dialogScene);
//        dialog.show();
//    }
//
//    private void addCommand(String cmd, String code) {
//        if(!userCommands.containsKey(cmd)) {
//            userCommands.put(cmd, code);
//            commands.appendText(cmd + "\n\n");
//        }
//    }

}