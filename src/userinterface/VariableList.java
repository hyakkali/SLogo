package userinterface;

import backend.Variable;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.HashMap;

public class VariableList extends ScrollPane{
    //Make a tableview
    private HashMap<TextArea, Variable > list = new HashMap<TextArea,Variable>();

    private Pane scrollable = new Pane();
    public VariableList(int XSIZE, int YSIZE)
    {
        setPrefWidth(XSIZE / 7 * 4);
        setPrefHeight(YSIZE / 7 * 2);
        setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    }

    public void addVariable(Variable v)
    {

        String name = v.getName();
        double info = v.getValue();
        if(!list.containsValue(v))
        {
            TextArea variableVal = new TextArea(String.valueOf(info));
            variableVal.setWrapText(true);
            variableVal.setPrefColumnCount(20);
            variableVal.setPrefRowCount(1);
            variableVal.setOnKeyPressed(k->{if(k.equals(KeyCode.ENTER)) changeVar(v.getName(),variableVal);});
            Text variableName = new Text(name +": ");
            HBox varNameCombo= new HBox();
            varNameCombo.getChildren().addAll(variableName,variableVal);
            list.put(variableVal,v);

            this.getChildren().add(varNameCombo);
        }
        else
        {
            for(TextArea t: list.keySet())
            {
                if(list.get(t).equals(v)) {
                    t.setText(String.valueOf(v.getValue()));
                    break;
                }
            }
        }
    }

    public void changeVar(String value, TextArea textArea)
    {
        try {
            double toSend = Double.valueOf(value);
            list.get(textArea).setValue(toSend);
        }
        catch(NumberFormatException n)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Please enter a double");
            alert.showAndWait();
        }
    }





}
