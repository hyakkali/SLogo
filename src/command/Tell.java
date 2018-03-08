package command;

import controller.Controller;
import java.util.Stack;

public class Tell implements Command{

    private CommandList myCommandList;
    private Stack<String> myStack;

    public Tell(CommandList commandList) {
        this.myCommandList = commandList;
        this.myStack = myCommandList.getInputStack();
    }

    @Override
    public double execute(Controller controller) {
        while (!myStack.isEmpty()) {
            controller.addActiveTurtles(myStack.pop());
        }
        return this.myCommandList.execute(controller);
    }

}
