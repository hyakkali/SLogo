package userinterface;

import backend.SLogoModel;
import command.Variable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import pen.LinePen;
import resources.languages.Language;
import resources.languages.LanguageFactory;
import turtle.Turtle;
import xml.ReadXML;
import xml.WriteXML;

import java.io.File;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;

public class UserScreen extends Application implements UserInterface{
    private static final String DEFAULT_RESOURCES = "resources.languages/";
    private static final String HELP_URL = "https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commandView.php";
    private static final String TITLE = "Slogo";

    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final int X_SIZE = 800;
    private static final int Y_SIZE = 600;

    private final double TURTLE_MOVE = 20.0;
    private final double PEN_THICKNESS = 0.5;
    private final double CONTEXT_X_OFFSET = 150.0;
    private final double CONTEXT_Y_OFFSET = 100.0;
    private final double TWO_PI = 360.0;

    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Scene myScene;

    private Consumer<String> parseText;
    private Consumer<Language> setSlogoLang;
    private Consumer<Void> getVariables;
    private Consumer<Void> newForm;


    private ResourceBundle cmdDescriptions;
    private ResourceBundle turtleImages;
    private ResourceBundle colors;
    private VariableList variableView;
    private ArrayList<Turtle> myTurtles;
    public ArrayList<Turtle> myActiveTurtles = new ArrayList<>();
    private HashMap<Integer, String> colorIntMap = new HashMap<>();
    private HashMap<Integer, String> imageIntMap = new HashMap<>();
    public List<Turtle> myInactiveTurtles = new ArrayList<>();
    private HashMap<Integer, String> colorMap = new HashMap<>();
    private HashMap<Integer, String> imageMap = new HashMap<>();

    private HashMap<String, String> userCommands = new HashMap<>();
    private TextArea commandView;
    private Pane turtlePane;
    private Stage myStage;
    private String language = "English";
    private List<Line> lines = new ArrayList<>();
    private final String[] languageOptions = {"English", "Chinese", "French", "German", "Italian", "Portuguese", "Russian", "Spanish", "Urdu"};
    private Stack<State> history = new Stack<>();

    /* adds the turtles called for by controller to the userscreen
     */
    public UserScreen(ArrayList<Turtle> t, Stage stage, Consumer<Void> newView) {
        myTurtles = t;
        start(stage);
        newForm = newView;
    }

    /* imports commands from slogomodel that the UI needs
     */
    public void initializeBackend(SLogoModel s) {
        parseText = e -> s.parse(e);
        setSlogoLang = e -> s.setLanguage(e);
        getVariables = e -> {
            List<Variable> g = s.getVariables();
            addVariables(g);
        };
        setupProperties(language);
    }

    /* will be used to insantiate all of the visual elements in
     * in the slogo project and add to the scene which returns to
     * start --this calls the menu related functions
     */
    private Scene setupScene(int width, int length) {
        Group root = new Group();

        setupProperties("English");

        myScene = new Scene(root, width, length);
        myScene.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> saveState());//listens to the mouse and calls save when a mouse clicked / button pressed
        myScene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode().equals(KeyCode.ALT)) loadState();
        });

        VBox right = createSideMenu();
        HBox bottom = createBottomMenu();

        BorderPane form = new BorderPane();

        turtlePane = new Pane();
        turtlePane.setPrefSize(Y_SIZE * 5 / 6, Y_SIZE * 5 / 6);
        turtlePane.setStyle(colors.getString("WHITE"));
        turtleSetup();

        form.setRight(right);
        form.setBottom(bottom);
        form.setCenter(turtlePane);
        form.setPrefSize(X_SIZE, Y_SIZE);
        form.setTop(createTopMenu());

        root.getChildren().add(form);

        beginAnimationLoop();

        return myScene;
    }

    /* Initialize the turtles with context menus and
     * and put them in active or inactive lists
     */
    private void setupTurtleMouse() {
        for (Turtle turtle : myTurtles) {
            turtle.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    MouseButton button = event.getButton();
                    if (button == MouseButton.PRIMARY) {
                        if (event.getClickCount() == 2) {
                            if (myActiveTurtles.contains(turtle)) {
                                addMyActiveTurtles(turtle);
                            } else {
                                addActiveTurtles(turtle);
                            }
                        } else if (event.getClickCount() == 1) {
                            turtle.requestFocus();
                        }
                    } else if (button == MouseButton.SECONDARY) {
                        ObservableList<MenuItem> menu = createContextMenuList(turtle);
                        ContextMenu cMenu = MenuBuilder.buildContext(menu);
                        cMenu.show(turtle, turtle.getX() + CONTEXT_X_OFFSET, turtle.getY() + CONTEXT_Y_OFFSET);
                    }
                }
            });
        }
    }

    private void setupActiveTurtles() {
        for (Turtle turtle : myTurtles) {
            turtlePane.getChildren().add(turtle);
            if (turtle.getActive()) {
                myActiveTurtles.add(turtle);
            }
        }
    }

    private void setupTurtleKeys() {
        for (Turtle turtle : myTurtles) {
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
                        } else if (event.getCode() == KeyCode.I) {
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
        }
    }

    private void turtleSetup() {
        setupTurtleMouse();
        setupTurtleKeys();
        setupActiveTurtles();
    }

    /**
     * Adds turtle to active turtle list and removes it from inactive list if
     * inactive list contains turtle.
     *
     * @param turtle
     */
    public void addActiveTurtles(Turtle turtle) {
        myActiveTurtles.add(turtle);
        turtle.setActive(true);
        if (myInactiveTurtles.contains(turtle)) {
            myInactiveTurtles.remove(turtle);
        }
    }

    /**
     * Adds turtle to inactive turtle list and removes it from active list if
     * active list contains turtle.
     *
     * @param turtle
     */
    public void addMyActiveTurtles(Turtle turtle) {
        myInactiveTurtles.add(turtle);
        turtle.setActive(false);
        if (myActiveTurtles.contains(turtle)) {
            myActiveTurtles.remove(turtle);
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
        for (Turtle turtle : myActiveTurtles) {
            List<Line> tLines = turtle.pen.getLines();

            if(!tLines.isEmpty()) {
                Line line = tLines.get(tLines.size()-1);
                if(turtle.getX()!=turtle.getXEnd()) {
                		if(turtle.pen.getPenBoolean()) {
                			line.setEndX(line.getEndX()+turtle.getXSpeed()*elapsedTime);
                		}
                    turtle.setX(turtle.getX()+turtle.getXSpeed()*elapsedTime);
                }
                if(turtle.getY()!=turtle.getYEnd()) {
	                	if(turtle.pen.getPenBoolean()) {
	                		line.setEndY(line.getEndY()-turtle.getYSpeed()*elapsedTime);
	                	}
                    turtle.setY(turtle.getY()-turtle.getYSpeed()*elapsedTime);
                }
            }

        }
        for (Turtle turtle : myActiveTurtles) {
            drawLine(turtle);
        }
    }

    /* creates the scene within the stage by calling setScene
     * defines/ initializes the state and begins stepping
     */
    @Override
    public void start(Stage stage) {
        myStage = stage;
        myScene = setupScene(X_SIZE, Y_SIZE); // get the scene
        stage.setResizable(false);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
    }

    /* initializes the properties files containing value
     * key pairs for commandView, images, and colors
     * Also gives slogomodel the correct language and creates indicies for slogomodel to utilize
     */
    private void setupProperties(String lang) {
        turtleImages = ResourceBundle.getBundle(DEFAULT_RESOURCES + "TurtleImages");
        colors = ResourceBundle.getBundle(DEFAULT_RESOURCES + "Colors");
        int index = 0;
        for (String color : colors.keySet()) {
            colorIntMap.put(index, color);
            index++;
        }
        index = 0;
        for (String color : turtleImages.keySet()) {
            imageIntMap.put(index, color);
            index++;
        }
        try {
            cmdDescriptions = ResourceBundle.getBundle(DEFAULT_RESOURCES + lang + "Descriptions");
        } catch (MissingResourceException m) {
            cmdDescriptions = ResourceBundle.getBundle(DEFAULT_RESOURCES + lang);
        }
        LanguageFactory backendLanguage = new LanguageFactory();
        Language myLanguage = backendLanguage.getLanguage(lang);
        if (parseText != null)
            setSlogoLang.accept(myLanguage);

        language = lang;
    }


    /* Defines the sidemenu which contains comboboxes
     * for language, line colro, background color
     * images-- text areas for commandView and variableView
     * and a reset button
     */
    private VBox createSideMenu() {
        VBox interactives = new VBox();

        Button resetButton = MenuBuilder.buildButton("File:images/reset.png", e -> reset());
        Button helpButton = MenuBuilder.buildButton("File:images/help.png", e -> getHostServices().showDocument(HELP_URL));
        Button newWIndow = MenuBuilder.buildButton("File:images/new.png", e -> newForm.accept(null));
        HBox buttons = new HBox(resetButton, helpButton, newWIndow);


        buttons.setSpacing(20);
        buttons.setAlignment(Pos.CENTER);

        commandView = getCommandsList();
        variableView = new VariableList(X_SIZE, Y_SIZE);
        ScrollPane variableScroller = new ScrollPane(variableView);
        variableScroller.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        ObservableList<String> images = FXCollections.observableArrayList(new ArrayList<>(turtleImages.keySet()));
        ObservableList<String> languages = FXCollections.observableArrayList(languageOptions);

        ComboBox imageCombo = MenuBuilder.buildCombo("Turtle Image", images, e -> setTurtleImage(e));
        ComboBox lineCombo = MenuBuilder.buildCombo("Line Color", colors, e -> setPenColor(e));
        ComboBox background = MenuBuilder.buildCombo("Background Color", colors, e -> setBackgroundColor(e));
        ComboBox language = MenuBuilder.buildCombo("Language", languages, e -> setLanguage(e));

        interactives.setPrefWidth(200);
        interactives.setStyle("-fx-background-color: #008000");
        interactives.setPadding(new Insets(20, 10, 20, 10));
        interactives.setAlignment(Pos.CENTER);
        interactives.setSpacing(10);
        interactives.getChildren().addAll(language, background, imageCombo,
                lineCombo, commandView, variableScroller, buttons);

        return interactives;
    }

    /* Defines the location and creation of the bottom menu
     * which contains the console
     */
    private HBox createBottomMenu() {
        HBox interactives = new HBox();
        Console console = new Console(X_SIZE, Y_SIZE, i -> parse(i));
        interactives.setPrefHeight(Y_SIZE / 9 * 2);
        interactives.setStyle("-fx-background-color: #008000");
        interactives.setPadding(new Insets(20, 10, 20, 10));
        interactives.setAlignment(Pos.TOP_LEFT);
        interactives.setSpacing(10);
        interactives.getChildren().addAll(console);
        return interactives;
    }

    private MenuBar createTopMenu()
    {
        MenuBar topMenu = new MenuBar();
        Menu file = new Menu("File");
        MenuItem saveFile = new MenuItem("Save File");
        saveFile.setOnAction(e->saveFile());
        MenuItem savePref = new MenuItem("Save Preferences");
        savePref.setOnAction(e->safePref());
        MenuItem loadFile = new MenuItem("Load File");
        loadFile.setOnAction(e-> loadFile());
        MenuItem loadPref = new MenuItem("Load Preferences");
        loadPref.setOnAction(e-> loadPref());
        file.getItems().addAll(saveFile,savePref,loadFile,loadPref);
        topMenu.getMenus().addAll(file);
        return topMenu;
    }

    /* Initializes the shape and properties of the command area
     */
    private TextArea getCommandsList() {
        commandView = new TextArea();
        commandView.prefWidth(X_SIZE / 7 * 4);
        commandView.setPrefWidth(X_SIZE / 7 * 4);
        commandView.setPrefHeight(Y_SIZE / 7 * 2);
        commandView.setEditable(false);
        commandView.setWrapText(true);
        setupCommandsList();

        return commandView;
    }

    /*Initializes the command window with the cmdDescriptions
     * of each command from the properties/ user definition
     * and updates when new user ommands are defined
     */
    private void setupCommandsList() {
        commandView.clear();
        commandView.appendText("Inherent Commands: \n\n");
        for (String cmd : cmdDescriptions.keySet()) {
            commandView.appendText(cmd.toUpperCase() + "\n");
            commandView.appendText(cmdDescriptions.getString(cmd) + "\n\n");
        }
        commandView.appendText("User Defined Commands: \n\n");
        for (String command : userCommands.keySet())
            commandView.appendText(command + "\n\n");
    }

    /*
        Put this in turtle
     */
    private ObservableList<MenuItem> createContextMenuList(Turtle turtle) {
        ObservableList<MenuItem> menu = FXCollections.observableArrayList();
        MenuItem mItem0 = new MenuItem("ID: " + Double.toString(turtle.getID()));
        MenuItem mItem1 = new MenuItem("X: " + Double.toString(turtle.getX()));
        MenuItem mItem2 = new MenuItem("Y: " + Double.toString(turtle.getY()));
        MenuItem mItem3 = new MenuItem("Heading: " + Double.toString(turtle.getRotate() % TWO_PI));
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
        if (colors.containsKey(c.toString()))
            setBackgroundColor(c.toString());
    }


    /* called to update the form to show the path
     * whenever the location of turtle is changed
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
    public void printError(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText(s);
        alert.showAndWait();
    }


    public void setPenColor(double d) {
        int index = (int) d;
        setPenColor(colorIntMap.get(index));
    }

    public void setBackGroundColor(double d) {
        int index = (int) d;
    }

    public void setTurtleImage(double d) {
        int index = (int) d;
        setTurtleImage(imageIntMap.get(index));
    }

    public void setLanguage(double d) {
        int index = (int) d;
        setLanguage(languageOptions[index]);
    }


    /* Resets the turtle location, variableView, draw pane
     * and redraws the UI
     */
    public void reset() {
        for (Turtle t : myTurtles) {
            t.setToOrigin();
            t.setHeading(0);
            t.setXEnd(0);
            t.setYEnd(0);
        }
        clearLines();
    }

    public void clearLines() {
        for (Turtle turtle : myTurtles) {
            turtle.pen.clearLines();
        }
        for (Line l : lines) {
            turtlePane.getChildren().remove(l);
        }
        lines.clear();
    }

    /* Defines the actions to be taken
     *  when the user types in the console
     */
    private void parse(String command) {
        parseText.accept(command);
        getVariables.accept(null);
        saveState();

    }

    private void addVariables(List<Variable> vars) {
        for(Variable v:vars)
            variableView.addVariables(vars);
    }

    /*
        add turtles to the list as desired by the preference XML
     */
    public void setTurtleNum(int a){
        while(myTurtles.size()<a) {

            Turtle toAdd = new Turtle(new LinePen(), myTurtles.size() + 1);
            myTurtles.add(toAdd);
            myActiveTurtles.add(toAdd);
        }
        while(myTurtles.size()>a)
            myTurtles.remove(myTurtles.size()-1);
        setupTurtleKeys();
        setupTurtleMouse();
    }

    /* save the state of the current of the screen
        and clears the stack if it gets too big
     */
    private void saveState() {

        State toAdd = new State(myTurtles, lines, turtlePane.getStyle(), language);
        if (history.isEmpty() || !toAdd.equals(history.peek())) {
            history.push(toAdd);
        }

        if (history.size() > 10) {
            Stack<State> temporaryStack = new Stack<>();
            for (int a = 0; a < 5; a++) {
                temporaryStack.push(history.pop());
            }
            history.clear();
            while (!temporaryStack.isEmpty())
                history.push(temporaryStack.pop());
        }
    }

    /* reinitialize the state from a past state 'undo'
     */
    private void loadState() {
        if (!history.isEmpty()) {
            State load = history.pop();
            myTurtles.clear();
            myActiveTurtles.clear();
            turtlePane.getChildren().clear();
            myTurtles.addAll(load.pastTurtles);
            turtleSetup();
            lines.clear();
            lines.addAll(load.pastLines);
            setBackgroundColor(load.background);
            turtlePane.getChildren().addAll(lines);
        }
    }

    private void setPenColor(String color) {
        for (Turtle turtle : myTurtles) {
            if (turtle.getActive())
                turtle.pen.setPenColor(Color.web(color));
        }
    }

    /* Defines the onAction of the language combo box
     * re-initializes the properties files and updates menu
     */
    public void setLanguage(String s) {
        setupProperties(s);
        setupCommandsList();
    }

    /* Changes the background by accessung the properties table of colors
     * to decode the input value from the combobox
     */
    public void setBackgroundColor(String value) {
        if (value.contains("-fx-background-color:"))
            turtlePane.setStyle(value);
        else
            turtlePane.setStyle("-fx-background-color: " + value);
    }

    public void setTurtleImage(String image) {
        for (Turtle turtle : myTurtles) {
            turtle.setImage(image);
        }
    }

    private void saveFile() {
        WriteXML.saveFile(history.peek());
    }

    private void safePref() {
        WriteXML.savePref(turtlePane.getStyle(), language, myTurtles.size());
    }

    private void loadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/data/saved"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        try {
            File xml = fileChooser.showOpenDialog(myStage);
            State load = ReadXML.buildState(xml);
            history.push(load);
            loadState();
        } catch (IllegalStateException e) {
            printError("File could not b e read");
        }
    }

    private void loadPref() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/data/saved"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        try {
            File xml = fileChooser.showOpenDialog(myStage);
            ReadXML.buildPreference(xml, this);
        } catch (IllegalStateException e) {
            printError("File could not b e read");
        }
    }

}
