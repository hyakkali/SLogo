package command;

import controller.Controller;


public class Repeat implements Command{
	
	private double repeatTimes;
	
	public Repeat(Double times) {
		this.repeatTimes = times;
	}
	
	@Override
	public double execute(Controller controller) {
		
	}
	
}
