package userinterface;
/**
 * Interface for View interacting with Model.
 * @author dylanpowers
 *
 */
public interface View {

	/**
	 * Get the text currently in the console
	 * @return text in the console
	 */
	public String getConsoleText();
	
	
	public String handleConsoleInput();
}
