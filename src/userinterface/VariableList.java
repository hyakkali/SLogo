package userinterface;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.HashMap;
import java.util.List;
import command.Variable;

public class VariableList extends ScrollPane{
    //Make a tableview
    private HashMap<TextArea, Variable > list = new HashMap<TextArea,Variable>();

    private Pane scrollable = new Pane();
    public VariableList(int XSIZE, int YSIZE)
    {
        setPrefWidth(XSIZE / 7 * 4);
        setPrefHeight(YSIZE / 7 * 2);
    }

    public void addVariables(List<Variable> variables)
    {
        for(Variable v : variables) {
            String name = v.getName();
            double info = v.getValue();
            if (!list.containsValue(v)) {
                TextField variableVal = new TextField(String.valueOf(info));
                variableVal.setOnKeyPressed(k -> {
                    if (k.getCode().equals(KeyCode.ENTER)) changeVar(v.getName(), variableVal);
                });
                Text variableName = new Text(name + ": ");
                variableVal.setPrefWidth(160 - variableName.getText().length() * 5);
                variableVal.setAlignment(Pos.CENTER_RIGHT);
                HBox varNameCombo = new HBox();
                varNameCombo.getChildren().addAll(variableName, variableVal);
                list.put(variableVal, v);

                this.getChildren().add(varNameCombo);
            } else {
                for (TextField t : list.keySet()) {
                    if (list.get(t).getName().equals(name)) {
                        t.setText(String.valueOf(v.getValue()));
                        break;
                    }
                }
            }
        }
    }

    public void changeVar(String value, TextField textArea)
    {
        try {
            double toSend = Double.valueOf(textArea.getText());
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
