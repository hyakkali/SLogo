package userinterface;

import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.function.Consumer;
/* Author @Conrad
    Defines the responsabilities of the console and allows for history accessing
    send to userscreen for user input
 */


public class Console extends TextArea  {


    private History history = new History();
    private Consumer<String> eventToOccur;



    /* Defines the orientation and and onAction properties
     * for the text this and sends the reference to be
     * set to instance variable this
     */
    public Console (int XSIZE, int YSIZE, Consumer<String> event) {
        eventToOccur = event;
        this.prefWidth(XSIZE / 7 * 4);
        this.setPrefWidth(XSIZE);
        this.setPrefHeight(YSIZE);
        this.setEditable(true);
        this.setWrapText(true);
        this.setPromptText("Enter a command");
        this.setOnKeyPressed(k->thisHandler(k));
    }

    /* cycles forward through command list and
     * sets text value of this to next command
     */
    private void displayNext() {
        if(history.hasNext())
            this.setText(history.moveForward());

    }

    /* cycles back through command list and
     * sets text value of this to prev command
     */
    private void displayPrev() {
        if(history.hasPrev())
            this.setText(history.moveBack());
    }



    /* Defines the actions to be taken
     *  when the user types in the this
     */
    private void thisHandler( KeyEvent k) {
        if (k.getCode().equals(KeyCode.ENTER)) {
            k.consume();
            String textHolder=this.getText();
            eventToOccur.accept(textHolder);
            history.add(textHolder);
            this.setText("");
        }

        else if (k.getCode().equals(KeyCode.UP)) {
            this.displayPrev();
        }
        else if (k.getCode().equals(KeyCode.DOWN)) {
            this.displayNext();
        }
    }
    /* Defines the actions to be taken
     *  when the user types in the this
     */

}
