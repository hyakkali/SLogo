package backend;

import resources.constants.Constants;
import resources.languages.Language;
import commandFactory.CommandFactory;
import controller.Controller;
import command.*;
import java.util.List;
import java.util.Stack;

import java.util.ArrayList;

/**
 * Class to handle parsing and executing of commands. Contains the main function that reads in user
 * inputs and runs the commands recursively, from the end to the beginning.
 * @author Dylan Powers
 * @author Alexi Kontos
 */
public class Executor {

    private Parser syntaxParser;
    private Language myLang;
    private Controller myController;
    private CommandFactory commandFactory;
    
    /**
     * Constructs an Executor object to parse and execute commands
     * @param ctrl the controller for this simulation
     * @param myFactory the factory to generate commands with
     */
    protected Executor(Controller ctrl, CommandFactory myFactory) {
        syntaxParser = new Parser(Language.SYNTAX);
        myController = ctrl;
        commandFactory = myFactory;
    }

    /**
     * Set the language of the given parser.
     * @param lang the language to be set to
     */
    protected void setMyLanguage(Language lang) {
        myLang = lang;
    }

    /**
     * Parse the text and execute commands accordingly. 
     * @param input the input from the console, in Stack form
     * @param myData the current data associated with the workspace
     * @return the final value after all of the commands have been run
     */
    protected double parseText(Stack<String> input, SLogoData myData) {
        ArrayList<Double> myParameters = new ArrayList<>();
        Parser languageParser = new Parser(myLang);
        while (!input.isEmpty()) {
        		//System.out.println(syntaxParser.getSymbol(input.peek()));
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
            
            // constants
            else if (syntaxParser.getSymbol(input.peek()).equals("Constant")) {
                Double value = Double.parseDouble(input.pop());
                myParameters.add(value);
            }

            // variable
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
            
            // deal with lists here
            else if (syntaxParser.getSymbol(input.peek()).equals("ListEnd")) {
                Stack<String> tempStack = new Stack<>();
                // pop once to get ride of the ListEnd, which we don't want on the stack
                input.pop();
                while (true) {
                		String arg = input.pop();
                		// don't add to tempStack if this is end of list
                		if (syntaxParser.getSymbol(arg).equals("ListStart")) break;
                		tempStack.push(arg);
                }
                // reverse the stack so that the later commands are on the top
                Stack<String> reversedStack = reverseStack(tempStack);
                // recurse here by adding the result of the next call to the parameters
                myParameters.add(parseText(reversedStack, myData));
            }
            // if we see a list start without first seeing a list end. Think Stack
            else if (syntaxParser.getSymbol(input.peek()).equals("ListStart")) {
                throw new IllegalArgumentException(Constants.DEFAULT_RESOURCES.getString("MissingOpenDelimiterError"));
            } else {
            		// no valid symbols
                throw new IllegalArgumentException(Constants.DEFAULT_RESOURCES.getString("InvalidSyntaxError"));
            }
        }
        // if we have gotten this far, there is only one return value, so return first index
        return myParameters.get(0);
    }
    
    /**
     * Method to reverse a stack.
     * @param stack the stack to be reversed
     * @return the reversed stack
     */
    private Stack<String> reverseStack(Stack<String> stack) {
    		Stack<String> ret = new Stack<>();
    		while (!stack.empty()) {
    			ret.push(stack.pop());
    		}
    		return ret;
    }
}
