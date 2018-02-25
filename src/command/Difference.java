package command;
/**
 * Returns the difference between two expressions
 * @author dylanpowers
 *
 */
public class Difference implements Command {

	private Double expr1;
	private Double expr2;
	
	/**
	 * Initialize with values of expr1 and expr2
	 */
	public Difference(Double expr1, Double expr2) {
		this.expr1 = expr1;
		this.expr2 = expr2;
	}
	
	/**
	 * Return the difference between these two expressions
	 */
	public double execute(Controller controller) {
		return this.expr1 - this.expr2;
	}
}
