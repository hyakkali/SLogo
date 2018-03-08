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
	
	public Repeat(double times) {
		this.repeatTimes = times;
	}
	
	@Override
	public double execute(Controller controller) {
		return .01;
	}
}
