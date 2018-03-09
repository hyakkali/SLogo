package command;

import controller.Controller;

/**
 * Command that acts as a for loop, executing commands accordingly.
 * @author dylanpowers
 *
 */
public class For implements Command {
	
	private CommandList varStartEndInc;
	private Command commandList;
	private Variable variable;
	private double start;
	private double end;
	private double increment;
	private final String BAD_INPUT = "Start should always be less than end and increment should be positive for the For command.";
	
	/**
	 * Initialize this command with a list of commands to be executed and 
	 * another list containing the variable and its start, end, and increment values;
	 */
	public For(Command varStartEndInc, Command commandList) {
		this.varStartEndInc = (CommandList) varStartEndInc;
		this.commandList = commandList;
		// go in reverse order getting all necessary inputs from varStartEndInc
		String incrementString = this.varStartEndInc.getTopListCommand();
		this.increment = Double.parseDouble(incrementString);
		String endString = this.varStartEndInc.getTopListCommand();
		this.end = Double.parseDouble(endString);
		String startString = this.varStartEndInc.getTopListCommand();
		this.start = Double.parseDouble(startString);
		// check that start is less than or equal to end and increment is positive
		if (this.start > this.end || this.increment <= 0) {
			throw new CommandException(BAD_INPUT);
		}
		String var = this.varStartEndInc.getTopListCommand();
		this.variable = new Variable(var);
	}
	
	/**
	 * Execute the given command list from start to end incrementing by increment.
	 * Assign the current iteration value to the given variable each time so that it can be accessed by the commands.
	 * @param controller the controller to execute commands on
	 * @return the value of the last command executed, or 0 if no commands were executed
	 */
	@Override
	public double execute(Controller controller) {
		// add the variable to the controllers
		controller.getMyData().addVariable(this.variable);
		double ret = 0;
		for (double i = this.start; i <= this.end; i += this.increment) {
			this.variable.setValue(i);
			ret = this.commandList.execute(controller);
		}
		return ret;
	}
}
