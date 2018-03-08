package commandFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import command.Command;
import command.CommandException;
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
	
	/**
	 * Get and return a command that is characterized by a given command name
	 * @param commandName
	 * 				The name of the command to be run
	 * @param args
	 * 				Any necessary arguments - will be an array of size zero, one, or two
	 * @throws CommandException to represent a bad command
	 * @return a Command object that has been initialized
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Command command(String commandName) throws NullPointerException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class<?> clazz = (Class<?>) commands.get(commandName);
		Constructor<?> cons = clazz.getConstructor();
		return (Command) cons.newInstance();
	}
	
	/**
	 * Helper method to create an command based upon the number of parameters
	 * @param clazz 
	 * 				the Class that needs a constructor
	 * @param args
	 * 				the parameters to be fed in
	 * @return a command successfully created based upon the number of parameters
	 */
	private Command appropriateCommand(Class<?> clazz, ArrayList<Double> args){
		try {
        		if (args.size() == 0) {
        			Constructor<?> cons = clazz.getConstructor();
        			return (Command) cons.newInstance();
        		} else if (args.size() == 1) {
        			Constructor<?> cons = clazz.getConstructor(Double.class);
        			return (Command) cons.newInstance(args.get(0));
        		} else {
        			Constructor<?> cons = clazz.getConstructor(Double.class, Double.class);
        			return (Command) cons.newInstance(args.get(0), args.get(1));
        		}
		} catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
			// we don't want to throw 2 alerts, and this exception is a nested exception, so just return null
			throw new CommandException(CommandException.BAD_PARAMS);
		}
	}
}

