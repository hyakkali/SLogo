package backend;

import java.util.ArrayList;

import commandFactory.CommandFactory;
import resources.constants.Constants;
import resources.languages.Language;
import main.Controller;
import java.util.Stack;

public class SLogoModel {

    SLogoData myData;
    Executor myExecutor;
    CommandFactory myFactory;
    Controller myController;

    public SLogoModel(Controller ctrl) {
        myFactory = new CommandFactory();
        registerCommands(myFactory);
        myExecutor = new Executor(ctrl, myFactory);
    }

    private void registerCommands(CommandFactory cmdFact) {
        try {
            for (String key : Language.ENGLISH.getKeys()) {
                Class<?> regClass = Class.forName(key);
                cmdFact.registerCommand(key, regClass);
            }
        }
        catch (ClassNotFoundException e){
            System.out.println(Constants.DEFAULT_RESOURCES.getString("ClassNotDefinedError"));
        }
    }


    public void setLanguage(Language lang) {
        myExecutor.setMyLanguage(lang);
    }


    public void parse(String input) {
        Stack<String> inputStack = new Stack<>();
        for (String str : input.split("\\s+")) {
            inputStack.push(str);
        }
        myExecutor.parseText(inputStack, myData);
    }

    public void setController(Controller ctrl) {
        myController = ctrl;
    }
}
