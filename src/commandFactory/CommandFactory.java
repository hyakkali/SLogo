package commandFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import command.Command;
/**
 * Utilizes the Factory design pattern to create objects that implement the Command interface.
 * @author dylanpowers
 *
 */
public class CommandFactory {
	
	private HashMap<String, Class<?>> commands;
	
	public CommandFactory() { 
		commands = new HashMap<String, Class<?>>();
	}
	
	/**
	 * Register a new command to this factory. This improves flexibility, and should be performed at initialization.
	 * @param commandName the name of the command
	 * @param commandClass the Class corresponding to the name; should be the same (including case)
	 */
	public void registerCommand(String commandName, Class<?> commandClass) {
		commands.put(commandName, commandClass);
	}
	
	public Command command(String commandName, Object...args) {
		try {
			Class<?> commandClass = (Class<?>) commands.get(commandName);
			Constructor<?> commandConstructor = commandClass.getConstructor(args.getClass());
			return (Command) commandConstructor.newInstance(args);
		} catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
			// TODO handle exception 
		}
		return null;
	}
}

