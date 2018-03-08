package command;

import controller.Controller;
import command.Command;

/**
 * Repeats a given command a number of times specified by the user
 * @author dylanpowers
 *
 */
public class Repeat implements Command {
	
	private double repeatTimes;
	private Command commandList;
	
	/**
	 * Initialize Repeat command
	 * @param times the number of times to repeat
	 * @param commandList the list of commands to run
	 */
	public Repeat(double times, Command commandList) {
		this.repeatTimes = times;
		this.commandList = commandList;
	}
	
	/**
	 * Execute this command on the given controller
	 * @param controller the controller to execute these commands on
	 * @return the value of the last command executed
	 */
	@Override
	public double execute(Controller controller) {
		double ret = 0;
		System.out.println(this.repeatTimes);
		// execute according to the variable repeatTimes
		for (int i = 0; i < this.repeatTimes; i++) {
			ret = commandList.execute(controller);
			System.out.println(ret);
		}
		return ret;
	}
}
