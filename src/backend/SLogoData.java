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

    private List<Variable> myVariables;
    private Language myLanguage;
    private boolean showSelected;


    public SLogoData() {
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

    public Variable getVariable(String name) {
        for (Variable var:myVariables) {
            if (var.getName().equals(name)) {
                return var;
            }
        }
//        return null;
        throw new IllegalArgumentException(Constants.DEFAULT_RESOURCES.getString("UninitializedVariableError") + name);
    }

    public List<Variable> getMyVariables() {
        return myVariables;
    }

    public Language getLanguage() {
        return myLanguage;
    }

}
