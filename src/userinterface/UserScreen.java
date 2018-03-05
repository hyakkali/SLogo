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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import resources.languages.Language;
import resources.languages.LanguageFactory;
import turtle.Turtle;

import java.util.*;
import java.util.function.Consumer;

public class UserScreen extends Application
{
    private static final String DEFAULT_RESOURCES = "resources.languages/";
    private static final String helpURL = "https://www2.cs.duke.edu/courses/compsci308/spring18/assign/03_slogo/commands.php";
    private static final String TITLE = "Slogo";

    private static final int FRAMES_PER_SECOND = 10;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private static final int XSIZE = 800;
    private static final int YSIZE = 600;

    private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Scene myScene;
    private Stage myStage;

    private ResourceBundle descriptions;
    private ResourceBundle turtleImages;
    private ResourceBundle properties;
    private ResourceBundle colors;

    private ArrayList<Turtle> turtles = new ArrayList<Turtle>();
    private HashMap<String, String> userCommands = new HashMap<String,String >();
    private HashMap<Variable,Turtle> varsList = new HashMap<Variable,Turtle>();
    private Turtle myTurtle;
//    private Pen pen;
    private SLogoModel mySLogoModel;
    private VariableList variables;
    private TextArea commands;
    private Pane turtlePane;
    private String language = "English";
    private List<Line> lines = new ArrayList<Line>();
    private Timeline animation;
    private String[] languageOptions ={"English", "Chinese", "French", "German", "Italian", "Portuguese", "Russian", "Spanish"};
    private final WebView browser = new WebView();
    private final WebEngine webEngine = browser.getEngine();


//INITIALIZATION RELATED FUNCTIONS
    //SCENE RELATED FUNCTIONS_________________________________________________________________________

        public UserScreen(Turtle t){
        		this.myTurtle = t;
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

            setupProperties("English");

            VBox right = createSideMenu();
            HBox bottom = createBottomMenu();
            BorderPane form = new BorderPane();

            turtlePane = new Pane();
            turtlePane.setPrefHeight(500);
            turtlePane.setPrefWidth(500);
            
//            showContextMenu();
            
            myTurtle.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					MouseButton button = event.getButton();
					if(button==MouseButton.PRIMARY) {
						myTurtle.requestFocus();
						//add set active or inactive
					} else if(button==MouseButton.SECONDARY) {
						ObservableList<MenuItem> menu = createContextMenuList();
			        		ContextMenu cMenu = MenuBuilder.buildContext(menu);
	        				cMenu.show(myTurtle, XSIZE/2, YSIZE/2);
					}
				}
            		
            });
            
            turtlePane.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					if(event.getCode()==KeyCode.RIGHT) {
						myTurtle.setX(myTurtle.getX()+20);
					} else if(event.getCode()==KeyCode.LEFT) {
						myTurtle.setX(myTurtle.getX()-20);
					} else if(event.getCode()==KeyCode.UP) {
						myTurtle.setY(myTurtle.getY()-20);
					} else if(event.getCode()==KeyCode.DOWN) {
						myTurtle.setY(myTurtle.getY()+20);
					}
				}
            	
            });


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
            drawLine();
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

            Button resetButton = MenuBuilder.buildButton("File:images/reset.png",e->reset());
            Button helpButton = MenuBuilder.buildButton("File:images/help.png", e->getHostServices().showDocument(helpURL));
            HBox buttons= new HBox(resetButton,helpButton);
            buttons.setSpacing(20);
            buttons.setAlignment(Pos.CENTER);

            commands = getCommandsList();
            variables = new VariableList(XSIZE,YSIZE);

            ObservableList<String> images =FXCollections.observableArrayList(new ArrayList<String>(turtleImages.keySet()));
            ObservableList<String> languages =FXCollections.observableArrayList(languageOptions);

            ComboBox imageCombo = MenuBuilder.buildCombo("Turtle Image",images,e-> changeTurtleImage(e));
            ComboBox lineCombo = MenuBuilder.buildCombo("Line Color", colors, e->setPenColor(e));
            ComboBox background = MenuBuilder.buildCombo("Background Color", colors, e->changeBackground(e));
            ComboBox language = MenuBuilder.buildCombo("Language", languages, e->changeLanguage(e));

            interactives.setPrefWidth(200);
            interactives.setStyle("-fx-background-color: #008000");
            interactives.setPadding(new Insets(20, 10, 20, 10));
            interactives.setAlignment(Pos.CENTER);
            interactives.setSpacing(10);
            interactives.getChildren().addAll(language, background, imageCombo,
                    lineCombo, commands, variables, buttons);

            return interactives;
        }

        /* Defines the location and creation of the bottom menu
         * which contains the console
         */
        private HBox createBottomMenu() {
            HBox interactives = new HBox();
            Console console = new Console(XSIZE,YSIZE,i->parse(i));
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
        public void addPreviousCommand(String s) {
            //useablecmds.add(s);
        }

    //VARIABLE FUNCTIONS__________________________________________________________________________________________

        /* Creates properties of the variable textarea
         * and initialized the values from the vars hashmap
         */


        /* adds the variables from controller to the hashmap
         * and rewrites the variables to the textarea
         */

//VIEW RELATED FUNCTIONS
    //TURTLE FUNCTIONS____________________________________________________________________________________________

        /* Called by TurtleViewController to
         * make the turtle invisible on the form
         * in response to commands from backend
         */
        public void toggleTurtle(boolean t){myTurtle.setVisible(t);}
        
        private ObservableList<MenuItem> createContextMenuList() {
        		ObservableList<MenuItem> menu = FXCollections.observableArrayList();
        		MenuItem mItem1 = new MenuItem("X: "+Double.toString(myTurtle.getX()));
        		MenuItem mItem2 = new MenuItem("Y: "+Double.toString(myTurtle.getY()));
        		MenuItem mItem3 = new MenuItem("Heading: "+Double.toString(myTurtle.getRotate()%360.0));
        		MenuItem mItem4 = new MenuItem("Color: "+myTurtle.pen.getPenColor());
        		MenuItem mItem5 = new MenuItem("Up/Down: "+myTurtle.pen.getPenBoolean());
        		MenuItem mItem6 = new MenuItem("Thickness: "+myTurtle.pen.getPenWidth());
        		menu.addAll(mItem1,mItem2,mItem3,mItem4,mItem5,mItem6);
        		return menu;
        }

    //FORM FUNCTIONS____________________________________________________________________________________________

        /* Allows the controller to alter the+
         * background of the UI calls changebackground
         */
        public void setBackgroundColor(Color c){

            //Figure out what this is
            if(colors.containsKey(c.toString()))
                changeBackground(c.toString());
        }


        /* called to update the form to show the path
         * whenever the locatoun of turtle is changed
         */
        private void drawLine() {
            for(Line l:myTurtle.pen.getLines())
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
//ACTIONS EVENTS
    /* Resets the turtle location, variables, draw pane
     * and redraws the UI
     */
    private void reset() {
//        myTurtle.setToOrigin();
//        myTurtle.setHeading(0);
//        for(Line l: lines)
//        {
//            turtlePane.getChildren().remove(l);
//        }
//        lines.clear();
//        pen.clearLines();
        variables.addVariable(new Variable("Test",.9));

    }

    /* Defines the actions to be taken
     *  when the user types in the console
     */
    private void parse( String  command) {
            mySLogoModel.parse(command);
            addVariable();
    }

    private void setPenColor(String color)
    {
        myTurtle.pen.setPenColor(Color.web(color));
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
    private void changeBackground(String value) {
        turtlePane.setStyle("-fx-background-color: " + value);
    }

    private void changeTurtleImage(String image)
    {
        myTurtle.setImage(image);
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