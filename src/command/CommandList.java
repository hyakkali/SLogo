package command;

import java.util.List;
import java.util.Stack;

import controller.Controller;

/**
 * Represents a list of commands that have been enclosed by brackets
 * @author dylanpowers
 *
 */
public class CommandList implements Command {

	private Stack<String> commands;
	/**
	 * Initialize this command list with a list of commands
	 * @param commands the commands to be contained in this list
	 */
	public CommandList(Stack<String> commands) {
		this.commands = commands;
	}

	/**
	 * Execute ALL of the commands in this list.
	 * @param controller the controller for these commands to be executed on
	 */
	@Override
	public double execute(Controller controller) {
		return 0;
	}
}
