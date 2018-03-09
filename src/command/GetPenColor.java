package command;

import controller.Controller;

public class GetPenColor implements Command {


    public GetPenColor() {
    }

    @Override
    public double execute(Controller controller) {
        return controller.getPenColor();
    }
}
