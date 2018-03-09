package command;

import controller.Controller;

public class SetPenColor implements Command {

    private Double myColorID;

    public SetPenColor(Double colorID) {
        this.myColorID = colorID;
    }

    @Override
    public double execute(Controller controller) {
        controller.setMyColor(this.myColorID);
        return myColorID;
    }
}
