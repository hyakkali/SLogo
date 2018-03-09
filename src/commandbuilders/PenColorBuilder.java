package commandbuilders;

import backend.Executor;
import command.Command;
import command.PenColor;
import controller.Controller;

public class PenColorBuilder extends CommandBuilder{
    /**
     * @Author Alexi Kontos
     * Class to create SetShape Command
     */

    public PenColorBuilder(){
    }

    @Override
    public Command build(Controller controller, Executor context) {
        return new PenColor();
    }
}
