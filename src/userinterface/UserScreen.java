package userinterface;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserScreen extends Application{
	
	 	private static final String TITLE = "Slogo";
	 	private static final int SIZE = 400;
	 	private static final int FRAMES_PER_SECOND = 60;
	 	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	 	private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	 	private static final Paint BACKGROUND = Color.AZURE;
	  
	    private Scene myScene;
	    
	    private ImageView turtle= new ImageView();
	    private ArrayList<String> commands = new ArrayList<String>();
	    private TextField commandbox = new TextField();
	    
	    
	    
	    
	    /*will be used to draw the form on initialization 
    	 * and hopefully recycled to redraw after the user 
    	 * changes the shape of the window
    	 */
	    private void drawForm(int width, int length)
	    {
	    	
	    }
	    
	    /*will be used to insantiate all of the visual elements in 
	     * in the slogo project and add to the scene
	     */
	    private Scene setScene (int width, int length)
	    {
	    	
	    	Group root = new Group();
	    	myScene = new Scene(root, width, length);
	    	
	    	BorderPane form = new BorderPane();
	    	SideMenu sidemenu= new SideMenu();
	    	form.setRight(sidemenu.getMenu());
	    	root.getChildren().add(form);
	    	return myScene;
	    }
	    	
	    	
	    
	    
	    /*creates the scene within the stage by calling setScene
	     * defines the keys necessary for the project
	     * initializes the state and begins stepping
	     */
	    @Override
	    public void start (Stage stage) {
	        myScene = setScene(SIZE, SIZE);
	       
	        
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
	    public static void main (String[] args) 
	    {
	        launch(args);
	    }
	    
	    /* called to update the turtle location 
	     * whenever the stuff is changed
	     */
	    private void step (double elapsedTime) 
	    {
	    	
	    }


}
