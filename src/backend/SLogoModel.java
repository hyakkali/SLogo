package backend;

import controller.Controller;
import resources.languages.Language;

import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

import command.*;
import userinterface.UserScreen;

public class SLogoModel {

    SLogoData myData;
    Executor myExecutor;
    Controller myController;

    public SLogoModel(Controller ctrl) {
        this.myController = ctrl;
        myData = new SLogoData();
        myExecutor = new Executor(ctrl);
    }

    public void setLanguage(Language lang) {
        myExecutor.setMyLanguage(lang);
    }

    public List<Variable> getVariables()
    {
        return myData.getMyVariables();
    }

    @SuppressWarnings("serial")
    public void parse(String input) {
        Stack<String> inputStack = new Stack<>();
        for (String str : input.split("\\s+")) {
            System.out.println(str + "\n");
            inputStack.push(str);
        }
        myController.addPreviouslyRunCommand(input);

        myExecutor.parseText(inputStack, this.myData);
        for(Variable v :myData.getMyVariables())
        System.out.print(v.getName());
    }

    public void setController(Controller ctrl) {
        myController = ctrl;
    }

    public SLogoData getMyData() {
        return myData;
    }
}
