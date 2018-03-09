package command;

import controller.Controller;

public class SetBackground implements Command {

    private Double myColorID;

    public SetBackground(Double colorID) {
        this.myColorID = colorID;
    }

    @Override
    public double execute(Controller controller) {
        controller.setMyBackground(this.myColorID);
        return this.myColorID;
    }
}
