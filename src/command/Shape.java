package command;

public class Shape implements Command {

    public Shape() {
    }

    @Override double execute(Controller controller) {
        controller.getShapeIndex();
        return 0.0;
    }
}
