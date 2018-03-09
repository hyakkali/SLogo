package backend;

import resources.languages.Language;
import commandbuilders.*;
import controller.Controller;
import command.*;
import java.util.Stack;
/**
 * Class to handle parsing and executing of commands. Contains the main function that reads in user
 * inputs and runs the commands recursively, from the end to the beginning.
 * @author Dylan Powers
 * @author Alexi Kontos
 */
public class Executor {

	private final String BUILDER_PREFIX = "commandbuilders.";
	private final String BUILDER_SUFFIX = "Builder";
    private Parser syntaxParser;
    private Language myLang;
    private Controller myController;
    // represents the current input into this executor
    private Stack<Command> currentInput;
    /**
     * Constructs an Executor object to parse and execute commands
     * @param ctrl the controller for this simulation
     * @param myFactory the factory to generate commands with
     */
    protected Executor(Controller ctrl) {
        syntaxParser = new Parser(Language.SYNTAX);
        myController = ctrl;
        currentInput = new Stack<>();
    }

    /**
     * Set the language of the given parser.
     * @param lang the language to be set to
     */
    protected void setMyLanguage(Language lang) {
        myLang = lang;
    }

    /**
     * Parse the text and pass the input to be evaluated and put them on a stack,
     * and then execute the top-most command
     * @param input the input from the console, in Stack form
     * @param myData the current data associated with the workspace
     * @return the final value after all of the commands have been run
     */
    public double parseText(Stack<String> inputStack, SLogoData data) {
    		evaluate(inputStack, data);
    		double ret = 0;
    		while (!currentInput.isEmpty())
    			ret = currentInput.pop().execute(myController);
    		return ret;
    }

    /**
     * Evaluates the input to generate the stack of commands to execute, often recursively
     * @param input the input to be evaluated, in Stack form
     * @param myData the current data associated with the workspace
     */
    private void evaluate(Stack<String> inputStack, SLogoData data) {
        Parser languageParser = new Parser(myLang);
        try {
            while (!inputStack.isEmpty()) {
            		// command
                if (syntaxParser.getSymbol(inputStack.peek()).equals("Command")) {
                		// name of command
                		String rawCommand = inputStack.pop();
                		String commandName = languageParser.getSymbol(rawCommand);
                		// retrieve builder
                		CommandBuilder builder = getBuilder(commandName);
                		Command command = builder.build(myController, this);
                    	currentInput.push(command);
                	// constants
                } else if (syntaxParser.getSymbol(inputStack.peek()).equals("Constant")) {	
                		// don't need a builder, because all we want to do is create a double based upon the next token
                		Command constantCommand = new Constant(Double.parseDouble(inputStack.pop()));
                		currentInput.push(constantCommand);
                		
                	// since the inputStack goes from the end of the input inwards, we will encounter the ListEnd first
                } else if (syntaxParser.getSymbol(inputStack.peek()).equals("ListEnd")) {
                		// get rid of the end
                		inputStack.pop();
                		Stack<String> temp = new Stack<>();
                		// loop until we see the ListStart
                		while (!syntaxParser.getSymbol(inputStack.peek()).equals("ListStart")) {
                			temp.push(inputStack.pop());
                		}
                		// get rid of the list start
                		inputStack.pop();
                		// this is a list of commands, go ahead and instantiate it right away
                		Command commandList = new CommandList(reverseStack(temp), this, data);
                		currentInput.push(commandList);
                }
                
                // variables
                else if (syntaxParser.getSymbol(inputStack.peek()).equals("Variable")) {
                		// name of the variable
                		String var = inputStack.pop();
                		// check if the variable already exists
                		if (data.getVariable(var) != null) {
                			Command v = data.getVariable(var);
                			currentInput.push(v);
                		} else {
                			// we know this has to be a variable, so instantiate it
                			Command v = new Variable(var);
                			currentInput.push(v);
                		}
                		
                	// the symbol cannot be found
                } else {
                		throw new CommandException();
                }
            }
        } catch (Exception e) {
        		throw new CommandException();
        }
    }
    
    /**
     * Get the next command from the current input stack of commands. Often used in builders.
     * @return the next Command object on the input stack
     */
    public Command getNextCommand() {
        return currentInput.pop();
    }

    /**
     * Retrieves the builder needed for a certain command. Also, REFLECTION!!!
     * @param commandName the name of the command to be executed
     * @return a builder object to build the specified command
     * @throws Exception
     */
    private CommandBuilder getBuilder(String commandName) throws Exception {
    		// construct fully qualified path
    		String builderName = BUILDER_PREFIX + commandName + BUILDER_SUFFIX;
    		// System.out.println("current builder name: " + builderName);
    		Class<?> builderClass = Class.forName(builderName);
    		return (CommandBuilder) builderClass.getConstructor().newInstance();
    }

    /**
     * Helper method to reverse a Stack
     * @param input the stack to be reversed
     * @return a reversed version of the input
     */
    private Stack<String> reverseStack(Stack<String> input) {
        Stack<String> ret = new Stack<>();
        while (!input.isEmpty()) {
            ret.push(input.pop());
        }
        return ret;
    }
}
