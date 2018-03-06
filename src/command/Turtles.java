package command;
import controller.Controller;


public class Turtles implements Command {

    public Turtles() {
    }

    @Override
    public double execute(Controller controller) {
       return (double) controller.getNumTurtles();
    }
}
