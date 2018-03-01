package command;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Exception to be thrown when the user has either entered a command that does not exist
 * or a command with an invalid number of parameters.
 * 
 * The user should be informed as to exactly what they did wrong.
 * @author dylanpowers
 *
 */
public class CommandException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	// error messages
	public static final String DEFAULT_MESSAGE = "Something is wrong with the command as entered. Try again!";
	public static final String NON_EXISTENT = "The command %s does not exist. Please try again.";
	public static final String BAD_PARAMS = "The command as entered contains an invalid amount of parameters. Please try again.";
	
	/**
	 * Constructs the exception with the default message.
	 */
	public CommandException() {
		this(DEFAULT_MESSAGE);
	}

	/**
	 * Displays an alert with the given message, and throws the exception
	 * @param message the message to appear on the alert
	 */
	public CommandException(String message) {
		super(message);
		this.displayAlert(message);
	}

	/**
	 * Throw an exception with another different cause. The cause's message will be displayed in the alert.
	 * @param cause the exception that caused this
	 */
	public CommandException(Throwable cause) {
		super(cause);
		this.displayAlert(cause.getMessage());
	}
	
	/**
	 * Throw an exception based upon a command that is non-existent.
	 * @param message the message to be displayed on the alert
	 * @param commandName the name of the command that could not be found
	 */
	public CommandException(String message, String commandName) {
		super(message);
		this.displayAlert(String.format(message, commandName));
	}

	/**
	 * Throw an exception caused by another exception, but with a different message
	 * @param message the message to be displayed on the alert
	 * @param cause the exception that caused this
	 */
	public CommandException(String message, Throwable cause) {
		super(message, cause);
		this.displayAlert(message);
	}
	
	/**
	 * Displays an alert with the given message.
	 * @param message the message to appear with the alert
	 */
	private void displayAlert(String message) {
		Alert a = new Alert(AlertType.ERROR);
		a.setContentText(message);
		a.showAndWait();
	}

}
