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
     * Get the next command to be executed
     */

    /**
     * Parse the text and pass the input to be evaluated and put them on a stack,
     * and then execute the top-most command
     * @param input the input from the console, in Stack form
     * @param myData the current data associated with the workspace
     * @return the final value after all of the commands have been run
     */
    public double parseText(Stack<String> inputStack) {
        evaluate(inputStack);
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
    private void evaluate(Stack<String> inputStack) {
        Parser languageParser = new Parser(myLang);
        Stack<Command> tokenStack = new Stack<>();
        while (!inputStack.isEmpty()) {
            // command
            if (syntaxParser.getSymbol(inputStack.peek()).equals("Command")) {
                // name of command
                String commandName = languageParser.getSymbol(inputStack.pop());
                // retrieve builder
                CommandBuilder builder = getBuilder(commandName);
                Command command = builder.build(myController, this);
                currentInput.push(command);
                // constants
            } else if (syntaxParser.getSymbol(inputStack.peek()).equals("Constant")) {
                // don't need a builder, because all we want to do is create a double based upon the next token
                Command constantCommand = new Constant(Double.parseDouble(inputStack.pop()));
                currentInput.push(constantCommand);
                System.out.println("Size: " + currentInput.size());

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
                Command commandList = new CommandList(reverseStack(temp), this);
                currentInput.push(commandList);
            }
        }
    }


    public Command getNextCommand() {
        return currentInput.pop();
    }

    /**
     * Retrieves the builder needed for a certain command.
     * @param commandName the name of the command to be executed
     * @return a builder object to build the specified command
     */
    private CommandBuilder getBuilder(String commandName) {
        // construct fully qualified path
        try {
            String builderName = "commandbuilders." + commandName + "Builder";
            Class<?> builderClass = Class.forName(builderName);
            return (CommandBuilder) builderClass.getConstructor().newInstance();
        } catch (Exception e) {
            // command does not exist
            throw new CommandException(CommandException.NON_EXISTENT, commandName);
        }
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
