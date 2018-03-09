package command;

import controller.Controller;

public class Shape implements Command {

    public Shape() {
    }

    @Override
    public double execute(Controller controller) {
        controller.getShapeIndex();
        return 0.0;
    }
}
