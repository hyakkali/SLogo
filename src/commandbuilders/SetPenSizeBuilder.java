package commandbuilders;

import backend.Executor;
import command.Command;
import command.SetPenSize;
import controller.Controller;

public class SetPenSizeBuilder extends CommandBuilder {

    /**
     * @Author Alexi Kontos
     * Class to create SetPenSize Command
     */
    public SetPenSizeBuilder() {
    }

    @Override
    public Command build(Controller controller, Executor context) {
        return new SetPenSize(context.getNextCommand().execute(controller));
    }
}
