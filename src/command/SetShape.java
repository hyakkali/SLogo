package command;

import controller.Controller;

public class SetShape implements Command{

    private Double myShapeID;

    public SetShape(Double shapeID) {
        this.myShapeID = shapeID;
    }

    @Override
    public double execute(Controller controller) {
        controller.setMyShape(this.myShapeID);
        return this.myShapeID;
    }
}
