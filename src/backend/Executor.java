package backend;

import resources.constants.Constants;
import resources.languages.Language;
import commandFactory.CommandFactory;
import main.Controller;
import command.*;

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
                if (myParameters.isEmpty()) {
                    myParameters.add(commandFactory.command(languageParser.getSymbol(input.pop())).execute(myController));
                }
                else {
                		//System.out.println(languageParser.getSymbol(input.peek()));
                    Command command = commandFactory.command(languageParser.getSymbol(input.pop()), myParameters);
                    Double temp = command.execute(myController);
                    myParameters.clear();
                    myParameters.add(temp);
                }
            }
            else if (syntaxParser.getSymbol(input.peek()).equals("Constant")) {
                Double value = Double.parseDouble(input.pop());
                myParameters.add(value);
            }


            else if (syntaxParser.getSymbol(input.peek()).equals("Variable")) {
                String var = input.pop();
                if (myData.getMyVariables() != null) {
                    Variable v = myData.getVariable(var);
                    myParameters.add(v.getValue());
                }
                else {
                    throw new IllegalArgumentException(Constants.DEFAULT_RESOURCES.getString("UndefinedVariableError"));
                }
            }


            else if (syntaxParser.getSymbol(input.peek()).equals("ListStart")) {
                Stack<String> tempStack = new Stack<String>();
                for (String s: input) {
                    if (!syntaxParser.getSymbol(s).equals("ListEnd")) {
                        tempStack.add(s);
                        input.remove(s);
                    }
                    else {
                        input.remove(s);
                        break;
                    }
                }
                parseText(tempStack, myData);
            }
            else if (syntaxParser.getSymbol(input.peek()).equals("ListEnd")) {
                    throw new IllegalArgumentException(Constants.DEFAULT_RESOURCES.getString("MissingOpenDelimiterError"));
            } else {
            		throw new IllegalArgumentException(Constants.DEFAULT_RESOURCES.getString("InvalidSyntaxError"));
            }
        }

    }

}
