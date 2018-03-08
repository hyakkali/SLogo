package backend;

import java.util.LinkedList;
import java.util.Queue;

import command.Command;
import command.CommandException;

/**
 * This class represents a list of current commands that are in the queue to be executed.
 * The purpose of this class is to act as a container so that the executor stays closed.
 * 
 * @author dylanpowers
 *
 */
public class CommandList {
	// to hold all of the commands
	private Queue<Command> commands;
	
	/**
	 * Create space for the list of commands
	 */
	public CommandList() {
		commands = new LinkedList<Command>();
	}
	
	/**
	 * To be called whenever the user wishes to run more commands - this updates the currently queued commands
	 * @param commands the new commands
	 */
	protected void setQueue(Queue<Command> commands) {
		this.commands = commands;
	}
	
	/**
	 * Get the next token in this queue
	 * @return the next token
	 */
	public Command nextToken() {
		if (!this.commands.isEmpty()) 
			return this.commands.poll();
		throw new CommandException(CommandException.BAD_PARAMS);
	}

}
