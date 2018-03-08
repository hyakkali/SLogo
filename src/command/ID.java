package command;

import controller.Controller;

public class ID implements Command {



    public ID() {
    }

    @Override
    public double execute(Controller controller) {
        return controller.getActiveTurtle();
    }


}
