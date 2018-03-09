package command;

import controller.Controller;

/**
 * Command to perform command(s) a certain number of times, and reassign a variable each time so that it can be accessed by the commands
 * @author dylanpowers
 *
 */
public class DoTimes implements Command {
	
	private Command commandList;
	private CommandList variablePlusLimit;
	private double limit;
	private Variable variable;
	private final String BAD_LIMIT = "Limit of DoTimes command must be greater than or equal to 1.";
	
	/**
	 * Initialize this command with all of the necessary instance variables.
	 */
	public DoTimes(Command variablePlusLimit, Command commandList) {
		this.variablePlusLimit = (CommandList) variablePlusLimit;
		this.commandList = commandList;
		// we have to extract limit and variable, which are contained in variablePlusLimit
		String limitString = this.variablePlusLimit.getTopListCommand();
		try {
        		this.limit = Double.parseDouble(limitString);
        		// make sure that we have a valid limit
        		if (this.limit < 1) {
        			throw new CommandException(BAD_LIMIT);
        		}
        		String var = this.variablePlusLimit.getTopListCommand();
        		System.out.println(String.format("limit: %s\nvar: %s\n", limitString, var));
        		this.variable = new Variable(var);
		} catch (Exception e) {
			// makes sure that the program alerts the user when something goes wrong
			throw new CommandException(CommandException.DEFAULT_MESSAGE);
		}
	}
	
	/**
	 * Executes the commands given in commandList and executing with a variable determined by variableLimit
	 * @param controller the controller to execute commands on
	 * @return value of the final command executed, or 0 if no commands are executed
	 */
	@Override
	public double execute(Controller controller) {
		// add the variable to the controller
		controller.getMyData().addVariable(this.variable);
		double ret = 0;
		for (int i = 1; i <= this.limit; i++) {
			// set the variable to i so that it can be accessed by the commands
			this.variable.setValue(i);
			ret = this.commandList.execute(controller);
		}
		return ret;
	}
	
	
}
