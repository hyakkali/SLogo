package command;

import java.util.List;
import java.util.Stack;

import backend.Executor;
import controller.Controller;

/**
 * Represents a list of commands that have been enclosed by brackets
 * @author dylanpowers
 *
 */
public class CommandList implements Command {

	private Stack<String> commands;
	private Executor executor;
	/**
	 * Initialize this command list with a list of commands
	 * @param commands the commands to be contained in this list
	 * @param executor the executor to execute these commands with
	 */
	public CommandList(Stack<String> commands, Executor executor) {
		this.commands = commands;
		this.executor = executor;
	}

	public Stack<String> getInputStack() {
		return this.commands;
	}

	/**
	 * Execute ALL of the commands in this list.
	 * @param controller the controller for these commands to be executed on
	 */
	@Override
	public double execute(Controller controller) {
		@SuppressWarnings("unchecked")
		Stack<String> copy = (Stack<String>) this.commands.clone();
		return this.executor.parseText(copy);
	}
}