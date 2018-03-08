package command;

import controller.Controller;

/**
 * Creates a command with an if-this-then-else structure
 * @author dylanpowers
 *
 */
public class IfElse implements Command {
	
	private double expr;
	private Command trueList;
	private Command falseList;
	
	/**
	 * Initializes this object with a set of true commands and a set of false commands
	 * @param expr the expression to be evaluated
	 * @param trueList the list of commands to be executed if the expression is true
	 * @param falseList the list of commands to be executed if the expression is false
	 */
	public IfElse(double expr, Command trueList, Command falseList) {
		this.expr = expr;
		this.trueList = trueList;
		this.falseList = falseList;
	}
	
	/**
	 * If expression is true, execute trueList; if not, execute falseList
	 * @param controller the controller to execute the commands on
	 * @return the value of the last command executed, or 0 if no commands were executed
	 */
	@Override
	public double execute(Controller controller) {
		double ret = 0;
		if (this.expr != 0)
			ret = this.trueList.execute(controller);
		else
			ret = this.falseList.execute(controller);
		return ret;
	}
	
}
