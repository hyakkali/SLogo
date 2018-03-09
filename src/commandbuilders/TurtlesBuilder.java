package commandbuilders;

import backend.Executor;
import command.Turtles;
import command.Command;
import controller.Controller;

public class TurtlesBuilder extends CommandBuilder {

    /**
     * @Author Alexi Kontos
     * Class to create Turtles Command
     */

    public TurtlesBuilder() {
    }

    @Override
    public Command build(Controller controller, Executor context) {
        return new Turtles();
    }
}
