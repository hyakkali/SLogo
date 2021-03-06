package commandbuilders;

import backend.Executor;
import command.Command;
import controller.Controller;
import command.Ask;
import command.CommandList;

/**
 * @author Alexi Kontos
 * Class to create Ask Command
 */
public class AskBuilder extends CommandBuilder {

    public AskBuilder() {

    }

    @Override
    public Command build(Controller controller, Executor context) {
        return new Ask((CommandList) context.getNextCommand(), context.getNextCommand());
    }
}
