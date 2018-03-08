package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MenuBuilder {

    private static final String DEFAULT_COLOR_PATH= "-fx-background-color: ";


    /* Defines the location and color options for setting the color
     * of the lines the turtle draws and returns to VBox to add to menu
     */
    public static ComboBox buildCombo(String prompt, ResourceBundle options,Consumer<String> event) {

        ComboBox<HBox> combobox = new ComboBox<>();
        int index = 0;
        for (String option : options.keySet()){
            Text name = new Text(index+ " " +option);
            HBox select = new HBox();
            select.setPrefHeight(10);
            select.setPrefWidth(110);
            select.setSpacing(5);
            select.setStyle(DEFAULT_COLOR_PATH+option);
            select.getChildren().addAll(name);
            combobox.getItems().add(select);
        index++;
        }
        combobox.setOnAction(e->event.accept(combobox.getValue().getStyle()));
        combobox.setPromptText(prompt);
        combobox.setPrefWidth(200);
        return combobox;
    }

    public static ComboBox buildCombo(String prompt, ObservableList<String> list,Consumer<String> event)
    {
        ComboBox<String> combobox = new ComboBox<>();

        int index = 0;
        for (String option : list){
            combobox.getItems().add(String.valueOf(index)+ " "+option);
            index++;
        }
        combobox.setPromptText(prompt);
        combobox.setOnAction(e->{event.accept(combobox.getValue().substring(combobox.getValue().indexOf(" ")+1));
            combobox.setPromptText(prompt);});
        combobox.setPrefWidth(200);
        return combobox;
    }
    
    public static ContextMenu buildContext(ObservableList<MenuItem> list) {
    		ContextMenu cMenu = new ContextMenu();
    		cMenu.getItems().addAll(list);
    		return cMenu;
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
