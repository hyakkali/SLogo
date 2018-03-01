package backend;

import resources.constants.Constants;
import resources.languages.Language;
import commandFactory.CommandFactory;
import controller.Controller;
import command.*;
import java.util.List;
import java.util.Stack;

import java.util.ArrayList;


public class Executor {

    private Parser syntaxParser;
    private Language myLang;
    private Controller myController;
    private CommandFactory commandFactory;

    protected Executor(Controller ctrl, CommandFactory myFactory) {
        syntaxParser = new Parser(Language.SYNTAX);
        myController = ctrl;
        commandFactory = myFactory;
    }

    protected void setMyLanguage(Language lang) {
        myLang = lang;
    }


    protected void parseText(Stack<String> input, SLogoData myData) {
        ArrayList<Double> myParameters = new ArrayList<>();
        Parser languageParser = new Parser(myLang);
        while (!input.isEmpty()) {
        		System.out.println(syntaxParser.getSymbol(input.peek()));
            if (syntaxParser.getSymbol(input.peek()).equals("Command")) {
            		// name of command
            		String commandName = input.pop();
            		try {
                    Double temp = commandFactory.command(languageParser.getSymbol(commandName), myParameters).execute(myController);
                    // only clear the parameters if we just used any
                    if (myParameters.size() > 0) {
                    		myParameters.clear();
                    }
                    myParameters.add(temp);
            		} catch (NullPointerException e) {
            			// if command does not exist, tell the user that
            			throw new CommandException(CommandException.NON_EXISTENT, commandName);
            		}
            }
            else if (syntaxParser.getSymbol(input.peek()).equals("Constant")) {
                Double value = Double.parseDouble(input.pop());
                myParameters.add(value);
            }


            else if (syntaxParser.getSymbol(input.peek()).equals("Variable")) {
                String var = input.pop();
                if (myData.getVariable(var) != null) {
                    Variable v = myData.getVariable(var);
                    myParameters.add(v.getValue());
                }
                else {
                		if (myParameters.isEmpty()) {
                			Variable newVar = new Variable(var, 0.0);
                			myParameters.add(newVar.getValue());
                		}
                		else {
                			Variable newVar = new Variable(var, 0.0);
                         myData.addVariable(newVar);
                         myParameters.add(newVar.getMyID());
                		}
                }
            }
            else if (syntaxParser.getSymbol(input.peek()).equals("ListEnd")) {
                Stack<String> tempStack = new Stack<>();
                input.pop();
                for (String s: input) {
                    if (!syntaxParser.getSymbol(s).equals("ListStart")) {
                        tempStack.add(s);
                    }
                    else {
                        input.remove(s);
                        break;
                    }
                }
                input.removeAll(tempStack);
                parseText(tempStack, myData);
            }
            else if (syntaxParser.getSymbol(input.peek()).equals("ListStart")) {
                    throw new IllegalArgumentException(Constants.DEFAULT_RESOURCES.getString("MissingOpenDelimiterError"));
            } else {
                throw new IllegalArgumentException(Constants.DEFAULT_RESOURCES.getString("InvalidSyntaxError"));
            }
        }
    }
}
