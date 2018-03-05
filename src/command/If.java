
package command;

import controller.Controller;

public class If implements Command {
	
	double val1;
	double val2;
	
	public void If(Double input1, Double input2) {
		this.val1 = input1;
		this.val2 = input2;
	}
	
	@Override
	public double execute(Controller controller) {
	}
	
}

