package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MenuBuilder {


    /* Defines the location and color options for setting the color
     * of the lines the turtle draws and returns to VBox to add to menu
     */
    public static ComboBox buildCombo(String prompt, ResourceBundle options,Consumer<String> event)
    {

        ObservableList<String> list = FXCollections.observableArrayList(new ArrayList<String>(options.keySet()));
        ComboBox<String> combobox = new ComboBox<>(list);
        combobox.setPromptText(prompt);
        combobox.setOnAction(e->event.accept(options.getString(combobox.getValue())));
        combobox.setPrefWidth(200);
        return combobox;
    }

    public static ComboBox buildCombo(String prompt, ObservableList<String> list,Consumer<String> event)
    {
        ComboBox<String> combobox = new ComboBox<>(list);
        combobox.setPromptText(prompt);
        combobox.setOnAction(e->event.accept(combobox.getValue()));
        combobox.setPrefWidth(200);
        return combobox;
    }

    public static Button buildButton(String prompt, Consumer<Void> event)
    {
        Button b = new Button();
        if(prompt.contains("File:"))
        {
            ImageView rimage = new ImageView(new Image(prompt));
            rimage.setFitWidth(30);
            rimage.setFitHeight(30);
            b.setGraphic(rimage);
        }
        else
        {
            b.setText(prompt);
        }

        b.setOnAction(e -> event.accept(null));
        return b;
    }



}
