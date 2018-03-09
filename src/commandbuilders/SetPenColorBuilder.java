package commandbuilders;

import backend.Executor;
import command.Command;
import command.SetPenColor;
import controller.Controller;

public class SetPenColorBuilder extends CommandBuilder {

    /**
     * @Author Alexi Kontos
     * Class to create SetPenColor Command
     */
    public SetPenColorBuilder() {
    }

    @Override
    public Command build(Controller controller, Executor context) {
        return new SetPenColor(context.getNextCommand().execute(controller));
    }
}
