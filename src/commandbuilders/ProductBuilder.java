package commandbuilders;

import backend.Executor;
import command.Command;
import command.Product;
import controller.Controller;

/**
 * Class to create a product command 
 * @author dylanpowers
 *
 */
public class ProductBuilder extends CommandBuilder {

	/**
	 * Empty constructor
	 */
	public ProductBuilder() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Build a product command.
	 * @param controller the controller to execute with
	 * @param context the rest of the commands
	 * @return a product command
	 */
	@Override
	public Command build(Controller controller, Executor context) {
		return new Product(context.getNextCommand().execute(controller), context.getNextCommand().execute(controller));
	}

}
