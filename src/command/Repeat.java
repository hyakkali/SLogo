
package command;

import controller.Controller;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Repeat implements Command{
	
	private double repeatTimes;
	
	public Repeat(Double times, ArrayList<String> commands) {
		this.repeatTimes = times;
	}
	
	@Override
	public double execute(Controller controller) {
		return .01;
	}
	
}

