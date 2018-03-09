package command;

import controller.Controller;
import java.util.Stack;
import turtle.Turtle;
import java.util.ArrayList;

public class Ask implements Command {

    private CommandList myTurtleList;
    private Command myCommandList;
    private Stack<String> myTurtleInput;
    private ArrayList<Turtle> askedTurtles;

    public Ask(CommandList turtleList, Command commandList) {
        this.myTurtleList = turtleList;
        this.myCommandList = commandList;
        this.myTurtleInput = myTurtleList.getInputStack();
    }

    @Override
    public double execute(Controller controller) {
        double retVal = 0.0;
        while (!myTurtleInput.isEmpty()) {
            if (controller.getAskedTurtle(myTurtleInput.peek()) != null) {
                askedTurtles.add(controller.getAskedTurtle(myTurtleInput.pop()));
            }
            else {
                myTurtleInput.pop();
            }

        }
        controller.tempActiveTurtles(askedTurtles);
        retVal = this.myCommandList.execute(controller);
        controller.resetActiveTurtles();
        return retVal;
    }
}
