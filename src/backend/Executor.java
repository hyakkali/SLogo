package backend;

public class Executor {

    private CommandFactory commandFactory; //Might need to change based on class names
    private Parser syntaxParser;
    private Language myLang;

    protected Executor() {
        commandFactory = new CommandFactory();
    }
}
