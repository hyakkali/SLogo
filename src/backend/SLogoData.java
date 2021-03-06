package backend;

import turtle.Turtle;
import resources.languages.Language;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import java.util.List;

import command.Variable;

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
            System.out.println("Deleted");
            System.out.println(v.getName());
            System.out.println(variableName);
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
        for (Variable var : myVariables) {
            if (var.getName().equals(name)) {
                return var;
            }
        }
        return null;
    }

    public List<Variable> getMyVariables() {
        return myVariables;
    }

    public Language getLanguage() {
        return myLanguage;
    }

}
