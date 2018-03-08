package command;

import controller.Controller;

public class Tell implements Command {

    private Command myCommandList;

    public Tell(Command commandList) {
        this.myCommandList = commandList;
    }

    @Override
    public double execute(Controller controller) {

    }

}
