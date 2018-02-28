package commandFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import command.Command;
import command.Forward;
import command.SetHeading;
import command.SetTowards;
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
			System.out.println(String.format("commandName is %s from CF FUCK YEAH MOTHERFUCKER", commandName));
			Class<?> commandClass = (Class<?>) commands.get(commandName);
			Constructor<?> commandConstructor = commandClass.getConstructor(Double.class);
//			return (Command) commandConstructor.newInstance((Double) args[0]);
			return new SetTowards(50.0,100.0);
		} catch (NoSuchMethodException e) {
			// TODO handle exception 
			e.printStackTrace();
			
		}
		return null;
	}
}

