package command;

import java.util.Stack;
import controller.Controller;

/**
 * Represents an if-this-then structure as a command
 * @author dylanpowers
 *
 */
public class If implements Command {
	
	private double expr;
	private Command commandList;
	
	/**
	 * Initialize this command with the expression to evaluate and the commands to execute if so
	 * @param expr the expression to be evaluated
	 * @param commandList the commands to be executed if the expression is not 0
	 */
	public If(double expr, Command commandList) {
		this.expr = expr;
		this.commandList = commandList;
	}
	
	/**
	 * Execute the given command list on the controller if the expression is not 0
	 * @param controller the controller to execute these commands on
	 * @return the value of the last command executed, or 0 if no commands are executed
	 */
	@Override
	public double execute(Controller controller) {
		double ret = 0;
		// execute iff 'expr' is not 0
		if (this.expr != 0) ret = this.commandList.execute(controller);
		return ret;
	}
	
}
