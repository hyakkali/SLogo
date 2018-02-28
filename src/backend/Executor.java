package backend;

import resources.constants.Constants;
import resources.languages.Language;
import commandFactory.CommandFactory;
import main.Controller;

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
     //  System.out.println(languageParser.getSymbols().toString());
        while (!input.isEmpty()) {
          //  System.out.println((syntaxParser.getSymbol(input.peek())));
            if (syntaxParser.getSymbol(input.peek()).equals("Command")) {
                System.out.print("hi");
                if (myParameters.isEmpty()) {
                    myParameters.add(commandFactory.command(languageParser.getSymbol(input.pop())).execute(myController));
                }
                else if (myParameters.size() == 1){
                    System.out.print("One Param Command");
                    Double temp = commandFactory.command(languageParser.getSymbol(input.pop()), myParameters.get(0)).execute(myController);
                    myParameters.clear();
                    myParameters.add(temp);
                }
                else {
                    System.out.print("Two Param Command");
                    Double temp = commandFactory.command(languageParser.getSymbol(input.pop()), myParameters.get(0), myParameters.get(1)).execute(myController);
                    myParameters.clear();
                    myParameters.add(temp);
                }
            }
            else if (syntaxParser.getSymbol(input.peek()).equals("Constant")) {
                Double value = Double.parseDouble(input.pop());
                myParameters.add(value);
                System.out.println(value);
            }


            else if (syntaxParser.getSymbol(input.peek()).equals("Variable")) {
                String var = input.pop();
                if (myData.getMyVariables() != null) {
                    Variable v = myData.getVariable(var);
                    myParameters.add(v.getValue());
                }
                else {
                    Variable newVar = new Variable(var, 0.0); //Where do I put the value into the variable
                    myData.addVariable(newVar);
                }
            }
            else if (syntaxParser.getSymbol(input.peek()).equals("ListStart")) {
                Stack<String> tempStack = new Stack<>();
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
            else {
                if (syntaxParser.getSymbol(input.peek()).equals("ListEnd")) {
                    throw new IllegalArgumentException(Constants.DEFAULT_RESOURCES.getString("MissingOpenDelimiterError"));
                }
            }
           // throw new IllegalArgumentException(Constants.DEFAULT_RESOURCES.getString("InvalidSyntaxError"));
        }

    }

}
