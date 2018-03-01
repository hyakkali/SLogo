package backend;

import java.util.ArrayList;

import commandFactory.CommandFactory;
import controller.Controller;
import resources.languages.Language;

import java.util.Stack;
import java.util.List;
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
        myData = new SLogoData();
        myExecutor = new Executor(ctrl, myFactory);
    }

    private void registerCommands(CommandFactory cmdFact) {
        for (String key : Language.ENGLISH.getKeys()) {
            try {
                	// create full qualified name to load in
                	String qualifiedName = "command." + key;
                	System.out.println(String.format("Class name: %s", qualifiedName));
                Class<?> regClass = Class.forName(qualifiedName);
                cmdFact.registerCommand(key, regClass);
            } catch (ClassNotFoundException e) {
            		// class name does not exist
            		throw new CommandException(e);
            }
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
        myExecutor.parseText(inputStack, myData);
            myController.addPreviouslyRunCommand(input);
        try {
            myExecutor.parseText(inputStack, myData);
        }
        catch (Exception e){
        		e.printStackTrace();
//            myController.displayText("Improper command! Try again!");
        }
    }

    public void setController(Controller ctrl) {
        myController = ctrl;
    }

    public SLogoData getMyData() {
        return myData;
    }
}
