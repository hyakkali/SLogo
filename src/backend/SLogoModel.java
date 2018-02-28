package backend;

import java.util.ArrayList;

import commandFactory.CommandFactory;
import resources.constants.Constants;
import resources.languages.Language;
import resources.languages.LanguageFactory;
import main.Controller;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;

public class SLogoModel {

    SLogoData myData;
    Controller myController;
    private Executor myExecutor;
    private CommandFactory myFactory;
    private LanguageFactory myLanguageFactory;


    public SLogoModel(Controller ctrl) {
        myFactory = new CommandFactory();
        myLanguageFactory = new LanguageFactory();
        registerCommands(myFactory, myLanguageFactory);
        myExecutor = new Executor(ctrl, myFactory);

    }

    private void registerCommands(CommandFactory cmdFact, LanguageFactory langFact) {
        Set<String> keySet = new HashSet(langFact.getLanguage("English").getKeys());
        try {
            for (String key : keySet) {
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
