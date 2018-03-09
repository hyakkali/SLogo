package commandbuilders;

import backend.Executor;
import command.Tell;
import command.Command;
import command.CommandList;
import controller.Controller;

public class TellBuilder extends CommandBuilder {

    /**
     * @Author Alexi Kontos
     * Class to create Tell Command
     */
    public TellBuilder() {
    }

    @Override
    public Command build(Controller controller, Executor context) {
        return new Tell((CommandList) context.getNextCommand());
    }
}
