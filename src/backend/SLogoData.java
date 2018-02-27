package backend;

import turtle.Turtle;
import resources.languages.Language;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import java.util.List;
import java.util.ArrayList;
import resources.constants.Constants;
import resources.languages.LanguageFactory;
import java.util.Arrays;


public class SLogoData {

    private List<Turtle> myTurtles;
    private List<Variable> myVariables;
    private Language myLanguage;
    private Group myRoot;
    private boolean showSelected;


    public SLogoData(Turtle startTurtle) {
        myTurtles = new ArrayList<>(Arrays.asList(startTurtle)); // will need to change once turtle is added
        myVariables = new ArrayList<>();
        myLanguage = LanguageFactory.getLanguage(Constants.DEFAULT_LANGUAGE);
        showSelected = false;
    }


    public void deleteVariable(String variableName) {
        for (Variable v: myVariables) {
            if (variableName.equals(v.getName())) {
                myVariables.remove(v);
                break;
            }
        }
    }

    public void addVariable(Variable newVar) {
        deleteVariable(newVar.getName());
        myVariables.add(newVar);
    }

    protected Variable getVariable(String name) {
        for (Variable var:myVariables) {
            if (var.getName().equals(name)) {
                return var;
            }
        }
        throw new IllegalArgumentException(Constants.DEFAULT_RESOURCES.getString("UninitializedVariableError") + name);
    }

    public Language getLanguage() {
        return myLanguage;
    }

}
