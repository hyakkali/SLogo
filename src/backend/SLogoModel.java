package backend;

import java.util.ArrayList;

import commandFactory.CommandFactory;
import resources.languages.Language;
import main.Controller;
import java.util.Stack;

public class SLogoModel {

    SLogoData myData;
    Executor myExecutor;
    CommandFactory myFactory;

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
            //fix later
        }
    }


    public void setLanguage(Language lang) {
        myExecutor.setMyLanguage(lang);
    }

    @SuppressWarnings("serial")
    public void parse(String input) {
        Stack<String> inputStack = new Stack<String>();
        for (String str : input.split("\\s+")) {
            inputStack.push(str);
        }
        myExecutor.parseText(inputStack, myData);
    }
}
