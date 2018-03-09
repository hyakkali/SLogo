package commandbuilders;

import backend.Executor;
import command.Command;
import command.SetBackground;
import controller.Controller;

public class SetBackgroundBuilder extends CommandBuilder {

    /**
     * @Author Alexi Kontos
     * Class to create SetBackground Command
     */
    public SetBackgroundBuilder() {
    }

    @Override
    public Command build(Controller controller, Executor context) {
        return new SetBackground(context.getNextCommand().execute(controller));
    }
}
