package command;

import controller.Controller;

public class PenColor implements Command {

    public PenColor() {
    }

    @Override
    public double execute(Controller controller) {
        controller.getPenColor();
        return 0.0;
    }
}
