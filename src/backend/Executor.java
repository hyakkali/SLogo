package backend;

import resources.constants.Constants;
import resources.languages.Language;

import javax.swing.plaf.SliderUI;
import java.util.List;
import java.util.ArrayList;


public class Executor {

    private CommandFactory commandFactory; //Might need to change based on class names
    private Parser syntaxParser;
    private Language myLang;

    protected Executor() {
        commandFactory = new CommandFactory();
        syntaxParser = new Parser(Language.SYNTAX);
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
                if (languageParser.getSymbol(input.get(0)).equals("PossibleFunction")) {
                    AbsTreeNode function = data.getFunction(input.get(0));
                    input.remove(0);
                    if (syntaxParser.getSymbol(input.get(0)).equals("ListStart")) {
                        int listEndIndex = findMatchingBrace(input);
                        List<String> block = new ArrayList<>(input.subList(0, listEndIndex+1));
                        function.addArg(parseText(data, block));
                        removeBeforeIndex(input, listEndIndex);
                    }
                    return function;
                }


                Command cmd = commandFactory.getCommand(languageParser.getSymbol(input.get(0)));
                input.remove(0);
                if (command is asking for user instruction) {
                    arguments.add(parseFunctionText(data, input));
                }


                while (input.size() > 0) {
                    if (cmd.isMathCommand()) {
                        if (parseText(data, new ArrayList<>(input)).isMathCmd() || arguments.size()==0) {
                            arguments.add(parseText(data, input));
                        }
                        else {
                            return new AbsTreeNode(cmd, null, null, 0.0, false, arguments, data);
                        }
                    }
                    else {
                        arguments.add(parseText(data, input));
                    }
                }
                return new AbsTreeNode(cmd, null, null, 0.0, false, arguments, data);
            }
            else if (syntaxParser.getSymbol(input.get(0)).equals("Variable")){
                String variable = input.get(0).substring(1);
                input.remove(0);
                return new AbsTreeNode(null, variable, null, 0.0, false, arguments, data);
            }
            else if (syntaxParser.getSymbol(input.get(0)).equals("Constant")) {
                double value = Double.parseDouble(input.get(0));
                input.remove(0);
                return new AbsTreeNode(null, null, null, value, false, arguments, data);
            }
            else if (syntaxParser.getSymbol(input.get(0)).equals("ListStart")) {
                int listEndIndex = findMatchingBrace(input);
                List<String> block = new ArrayList<>(input.subList(1, listEndIndex));
                removeBeforeIndex(input, listEndIndex);
                while (block.size() > 0) {
                    arguments.add(parseText(data, block));
                }
                return new AbsTreeNode(null, null, null, 0.0, true, arguments, data);
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

    private AbsTreeNode parseFunctionText(SLogoData data, List<String> input) {
        Parser languageParser = new Parser(myLang);
        List<AbsTreeNode> arguments = new ArrayList<>();
        String functionName;
        if (input.size() == 0) {
            return null;
        }
        else {
            if (languageParser.getSymbol(input.get(0)).equals(Constants.POSS_FUNC)) {
                functionName = input.get(0);
                input.remove(0);
            }
            else {
                throw new IllegalArgumentException(Constants.DEFAULT_RESOURCES.getString("UndeclaredFunctionError"));
            }
            parseBlockArgument(input, arguments, data);
            parseBlockArgument(input, arguments, data);
        }
        return new AbsTreeNode(null, null, functionName, 0.0, false, arguments, data);
    }

    private void parseBlockArgument(List<String> input, List<AbsTreeNode> args, SLogoData data) {
        if (syntaxParser.getSymbol(input.get(0)).equals("ListStart")) {
            int listEndIndex = findMatchingBrace(input);
            List<String> block = new ArrayList<>(input.subList(0, listEndIndex+1));
            removeBeforeIndex(input, listEndIndex);
            args.add(parseText(data, block));
        }
        else {
            throw new IllegalArgumentException(Constants.DEFAULT_RESOURCES.getString("UndeclaredFunctionError"));
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
