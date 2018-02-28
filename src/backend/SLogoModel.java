package backend;

import java.util.ArrayList;

import commandFactory.CommandFactory;
import resources.languages.Language;
import main.Controller;
import java.util.Stack;
import command.*;

public class SLogoModel {

    SLogoData myData;
    Executor myExecutor;
    CommandFactory myFactory;
    Controller myController;

    public SLogoModel(Controller ctrl) {
        myFactory = new CommandFactory();
        registerCommands(myFactory);
        this.myController = ctrl;
        myExecutor = new Executor(ctrl, myFactory);
    }

    private void registerCommands(CommandFactory cmdFact) {
        try {
            for (String key : Language.ENGLISH.getKeys()) {
            		// create full qualified name to load in
            		String qualifiedName = "command." + key;
            		System.out.println(String.format("Class name: %s", qualifiedName));
                Class<?> regClass = Class.forName(qualifiedName);
                cmdFact.registerCommand(key, regClass);
            }
        }
        catch (ClassNotFoundException e){
            // TODO Make this exception handling better
        		System.out.println("Error is here dumbass\n");
        		e.printStackTrace();
        		System.out.println("I said here\n");
        }
    }


    public void setLanguage(Language lang) {
        myExecutor.setMyLanguage(lang);
    }

    @SuppressWarnings("serial")
    public void parse(String input) {
        Stack<String> inputStack = new Stack<String>();
        for (String str : input.split("\\s+")) {
        		System.out.println(str + "\n");
            inputStack.push(str);
        }
        try {
            myExecutor.parseText(inputStack, myData);
        }
        catch (Exception e){
        		myController.displayText("Improper command! Try again!");
        }
    }

    public void setController(Controller ctrl) {
        myController = ctrl;
    }
}
