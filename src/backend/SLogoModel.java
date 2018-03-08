package backend;

import controller.Controller;
import resources.languages.Language;

import java.util.Stack;
import command.*;

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

    @SuppressWarnings("serial")
    public void parse(String input) {
        Stack<String> inputStack = new Stack<>();
        for (String str : input.split("\\s+")) {
            System.out.println(str + "\n");
            inputStack.push(str);
        }
        myController.addPreviouslyRunCommand(input);
        try {
            myExecutor.parseText(inputStack);
        }
        catch (Exception e){
        		throw new CommandException(e);
        }
    }

    public void setController(Controller ctrl) {
        myController = ctrl;
    }

    public SLogoData getMyData() {
        return myData;
    }
}
