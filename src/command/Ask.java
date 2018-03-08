package command;

import controller.Controller;

public class Ask implements Command {

    private Command myTurtleList;
    private Command myCommandList;

    public Ask(Command turtleList, Command commandList) {
        this.myTurtleList = turtleList;
        this.myCommandList = commandList;
    }

    @Override
    public double execute(Controller controller) {

    }
}
