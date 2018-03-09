package command;
import controller.Controller;

public class GetShape implements Command {

    public GetShape() {
    }

    @Override
    public double execute(Controller controller) {
        return controller.getShapeIndex();
    }
}
