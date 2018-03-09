package commandbuilders;

import backend.Executor;
import command.SetShape;
import command.Command;
import controller.Controller;

public class SetShapeBuilder extends CommandBuilder {

    /**
     * @Author Alexi Kontos
     * Class to create SetShape Command
     */

    public SetShapeBuilder(){
    }

    @Override
    public Command build(Controller controller, Executor context) {
        return new SetShape(context.getNextCommand().execute(controller));
    }
}
