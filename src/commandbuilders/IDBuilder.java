package commandbuilders;

import backend.Executor;
import command.ID;
import command.Command;
import controller.Controller;

public class IDBuilder extends CommandBuilder {


    /**
     * @Author Alexi Kontos
     * Class to create ID Command
     */
    public IDBuilder() {
    }

    @Override
    public Command build(Controller controller, Executor context) {
        return new ID();
    }
}
