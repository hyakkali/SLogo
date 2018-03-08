package command;

import controller.Controller;
import java.util.Stack;

public class Tell implements Command{

    private Command myCommandList;
    private Stack<String> myStack;

    public Tell(Command commandList) {
        this.myCommandList = commandList;
    }

    @Override
    public double execute(Controller controller) {

    }

}
