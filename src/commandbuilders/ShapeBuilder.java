package commandbuilders;

import backend.Executor;
import command.Command;
import command.Shape;
import controller.Controller;

public class ShapeBuilder extends CommandBuilder {

    /**
     * @Author Alexi Kontos
     * Class to create Shape Command
     */
    public ShapeBuilder() {

    }

    @Override
    public Command build(Controller controller, Executor context) {
        return new Shape();
    }
}
