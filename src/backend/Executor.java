package backend;

import resources.constants.Constants;
import resources.languages.Language;
import command.Command;
import commandFactory.CommandFactory;
import main.Controller;

import java.util.List;
import java.util.ArrayList;
import command.*;


public class Executor {

    private CommandFactory commandFactory; //Might need to change based on class names
    private Parser syntaxParser;
    private Language myLang;
    private Controller myController;


    protected Executor(Controller ctrl) {
        commandFactory = new CommandFactory();
        syntaxParser = new Parser(Language.SYNTAX);
        registerCommands(commandFactory);
        myController = ctrl;
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

    protected void setMyLanguage(Language lang) {
        myLang = lang;
    }

    protected AbsTreeNode parseText(SLogoData data, List<String> input) {
        Parser languageParser = new Parser(myLang);
        List<AbsTreeNode> arguments = new ArrayList<>();
        if (input.size() == 0) {
            return null;
        }
        else {
            if (syntaxParser.getSymbol(input.get(0)).equals("Command")) {
                Command cmd = commandFactory.command(languageParser.getSymbol(input.get(0)));
                input.remove(0);
                while (input.size() > 0) {
                        if (arguments.size()!=0) {
                            arguments.add(parseText(data, input));
                        }
                        else {
                            return new AbsTreeNode(cmd, null, 0.0, false, arguments, data, myController);
                        }
                }
                return new AbsTreeNode(cmd, null, 0.0, false, arguments, data, myController);
            }
            else if (syntaxParser.getSymbol(input.get(0)).equals("Variable")){
                String variable = input.get(0).substring(1);
                input.remove(0);
                return new AbsTreeNode(null, variable, 0.0, false, arguments, data, myController);
            }
            else if (syntaxParser.getSymbol(input.get(0)).equals("Constant")) {
                Double value = Double.parseDouble(input.get(0));
                input.remove(0);
                return new AbsTreeNode(null, null, value, false, arguments, data, myController);
            }
            else if (syntaxParser.getSymbol(input.get(0)).equals("ListStart")) {
                int listEndIndex = findMatchingBrace(input);
                List<String> block = new ArrayList<>(input.subList(1, listEndIndex));
                removeBeforeIndex(input, listEndIndex);
                while (block.size() > 0) {
                    arguments.add(parseText(data, block));
                }
                return new AbsTreeNode(null, null, 0.0, true, arguments, data, myController);
            }
            else {
                if (syntaxParser.getSymbol(input.get(0)).equals("ListEnd")){
                    throw new IllegalArgumentException(Constants.DEFAULT_RESOURCES.getString("MissingOpenDelimiterError"));
                }
                else {
                    throw new IllegalArgumentException(Constants.DEFAULT_RESOURCES.getString("InvalidSyntaxError") + input.get(0));
                }
            }
        }
    }

    private void removeBeforeIndex(List<String> list, int index) {
        while (index >= 0 ) {
            list.remove(0);
            index--;
        }
    }

    private int findMatchingBrace(List<String> input) {
        int counter = 0;
        for (int x = 0; x < input.size(); x ++) {
            if (input.get(x).equals("[")) {
                counter++;
            }
            else if (input.get(x).equals("]")){
                counter--;
                if (counter == 0) {
                    return x;
                }
            }
        }
        return 0;
    }
}
