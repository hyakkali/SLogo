package userinterface;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.util.ResourceBundle;
import java.util.function.Function;

public class SideMenu
{

    private VBox interactives = new VBox();
    private ResourceBundle properties;

    public SideMenu(UserScreen u)
    {

        //properties = p;
        Button resetButton = getResetButton();
        interactives.setPrefWidth(200);
        interactives.setStyle("-fx-background-color: #008000");
        interactives.setPadding(new Insets(5, 0, 5, 0));
        interactives.setAlignment(Pos.CENTER);
        interactives.getChildren().addAll(resetButton);
    }


    private Button getResetButton()
    {
        return new Button(properties.getString("Reset"));
    }

    public VBox getMenu()
    {
        return interactives;
    }

//        calculateOnShipments(l, new Function<UserScreen, Void>() {
//            public void apply(UserScreen s) { // The object
//
//            }
//    }



}
