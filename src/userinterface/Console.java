package userinterface;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.function.Consumer;
/* Author @Conrad
    Defines the responsabilities of the console and allows for history accessing
    send to userscreen for user input
 */


public class Console extends VBox {

    private CMDHistory history = new CMDHistory();
    private Consumer<String> eventToOccur;
    private VBox commandsContainer;
    private ScrollPane commandScroller;
    private TextArea commandWindow;


    /* Defines the orientation and and onAction properties
     * for the text this and sends the reference to be
     * set to instance variable this
     */
    public Console (int xsize, int ysize, Consumer<String> event) {
        eventToOccur = event;
        commandWindow = new TextArea();
        commandWindow.setPrefHeight(10);
        commandWindow.setEditable(true);
        commandWindow.setWrapText(true);
        commandWindow.setPromptText("Enter a command");
        commandWindow.setOnKeyPressed(k->thisHandler(k));


        commandsContainer = new VBox();
        commandScroller = new ScrollPane(commandsContainer);
        commandScroller.setPrefSize(xsize,ysize);
        this.getChildren().add(commandScroller);
        this.getChildren().add(commandWindow);
    }


    /* cycles forward through command list and
     * sets text value of this to next command
     */
    private void displayNext() {
        if(history.hasNext())
            commandWindow.setText(history.moveForward());

    }

    /* cycles back through command list and
     * sets text value of this to prev command
     */
    private void displayPrev() {
        if(history.hasPrev())
            commandWindow.setText(history.moveBack());
    }



    /* Defines the actions to be taken
     *  when the user types in the this
     */
    private void thisHandler( KeyEvent k) {
        if (k.getCode().equals(KeyCode.ENTER)) {
            k.consume();
            this.processCommand();
        }

        else if (k.getCode().equals(KeyCode.UP)) {
            this.displayPrev();
        }
        else if (k.getCode().equals(KeyCode.DOWN)) {
            this.displayNext();
        }
    }
    /*

     */
    private void processCommand(){
        String textHolder=commandWindow.getText();
        eventToOccur.accept(textHolder);
        history.add(textHolder);
        addPastCommand();
        commandWindow.setText("");
    }

    /*
        adds the commands to a list of
     */
    private void addPastCommand()
    {
        Text command = new Text(commandWindow.getText());
        command.setOnMouseClicked(e->{commandWindow.setText(command.getText()); processCommand();});
        commandsContainer.getChildren().add(command);
    }
}
