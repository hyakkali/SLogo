package commandbuilders;

import backend.Executor;
import command.*;
import controller.Controller;

public class ForwardBuilder extends CommandBuilder {
	
	private CommandList nextCommands;
	
	/**
	 * Initialize
	 */
	public ForwardBuilder() {}

	/**
	 * Move the turtle forward by creating the Forward object and executing it.
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		double val = context.getNextCommand().execute(controller);
		System.out.println(val);
		return new Forward(val);
	}
	
}
