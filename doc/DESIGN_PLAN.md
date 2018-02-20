SLogo Plan 
======
### Intro

In implementing this project, our team will create a functional, high level and user-friendly programming language designed for children to experiment with programming. The program will allow the user to update the color and position of a turtle sprite by typing single to multiple line codes referenced in the library of commands we write for the project. The project will be split up into several components-- the UI which is responsible for handling user input to the text console, displaying the information, and initializing the program, the backend which will take input from the UI and convert the slogo commands to java methods to be called on the turtle object in order to update it, hold data, and send it to the UI, and a controller which will manage the interactions between these two. To maintain efficient programming and decrease the work necessary to implement/ extend our design, we will ensure that the interactions between the UI and backend are as limited as possible-- our plan currently allows for the sharing of turtle information, sending and receiving text from the console, and getting basic information about the size of the form the turtle occupies. These interactions are to be rigid and remove the possibility of data being misused. We are designing with the intent of adding new commands and features, and we intend to do this by making modular classes and methods and by using the concept of properties tables. In general the backend will be most open to allow more complicated commands.

### Design Overview

This project makes use of 4 primary APIs -- Visualizer internal, Visualizer external, Backend internal, and Backend external with the following responsibilities: 
* Visualizer internal- 
    * Update the display based on the location and past movement of the turtle
    * input from the console as users type commands
    * Initialize the UI
* Visualizer external
    * Get/ set the position and orientation of the form and the turtle as the backend sends this information
    * Send information about the size of the drawing form to the backend
* Backend internal 
    * Translate parsed text into a list of commands to be executed on the turtle 
    * Execute these commands 
    * Maintain the library of slogo commands to be called
* Backend external 
    * Receive input from the parser/ front end side
    * Send the new information about turtle to the front end after each command
    * Generate a command list to send to the front end 

Within these APIs and the two ends of the project will be a variety of classes which will interact with each other as follows:
![](./images/CRC.jpg)

### User Interface 

The user interface will consist of a large window that displays the turtle and lines that reflect the turtle’s movement. On the right side, there will be a pane that includes a list of past and possible commands that the user can give to the turtle, a list of current environment variables, a drop-down menu allowing the user to choose the color of the turtle, and a button that allows the user to upload an image file to replace the default turtle image. There will also be a dropdown menu to allow the user to choose the background color for the turtle screen, another menu to choose the pen color, and another to choose the language of the commands. On the bottom, there will be a large text field where the user will input commands for the program to run and right next to the text field will be a button that will run the inputted commands when it is pushed. 
![](./images/UI.jpg)





### API Details

As described in the Design Overview, one of our biggest design goals is maintaining flexibility and readability throughout our project. We will be utilizing four main APIs in our project, below are in depth details about each:

* Internal Visualizer API: The internal visualizer’s job is mainly to handle and pass off the user input to the parser and backend for the translation into commands, update the display based on the location and movement of the turtle, and to initialize the UI. This API is intended to be used as a piece of the ‘read’ part of the read-eval-print loop of our program. The API will only need to be able to handle user input, as well as initializing the window and simple visualization pieces needed to show the program, so not very many resources will be necessary for this piece. It can be extended to add different visualization pieces that the user may want to add i.e. changing the look and style of the window the program runs in. The decision to create both the main and front end pieces allows for the separation of dependencies as well as making our program more readable.

* External Visualizer API: This API’s job is get and set the position of the turtle from the back-end, and to send information about the size and format of the drawing to the back-end. This API is intended to be used as pieces of both the ‘read’ and ‘eval’ pieces of the read-eval-print loop of the program. The API will be able to be extended in a few ways. The manner in which the position of the turtle is updated should not be changed very much, but this API will be able to be extended so that many different options of drawing and format can be utilized, i.e. different line styles, turtle shapes, etc. We are implementing the classes specified above in the External Visualizer API because we are able to separate many dependencies so that  the program will be easy to follow in its execution and debug, as well as keeping all manner of data manipulation inside of its own class so data is not being passed from piece to piece back and forth.

* Internal Backend API: This API’s job is is to translate in the parsed text into the commands needed to control the turtle, execute these commands, and to maintain the library of Slogo commands. This API can be extended so that many different commands can be added into the ‘library’. The manner in which the commands will be executed will also be able to be extended so that different movements can be added. The classes we plan to use in this API were chosen so that we can keep the command execution separate from the implementation in order to improve readability and flexibility. The translation of the parsed text as well will be separate so that we can complete each execution in its own class in order to avoid confusion between the pieces of the program.

* External Backend API: This API’s job is to receive and handle the input from the parser and visualization side of the program, send the new information from the back-end to the visualizer to update the turtle, and to generate a command list to be sent to the front end. This API can be extended similarly to the internal backend API. Extensions can include changes to the manner in which the turtle will update as new commands are added in, as well as creating new commands to show to the visualizer. The classes we plan to use in this API were chosen so that, again, we can keep flexibility and readability at the forefront of our design. By separating the sending of the new position information from the actual execution of the commands it makes it clear how the data is being executed, as well as making it far easier to debug and add onto throughout the project’s lifespan.

### API Example Code

* Suppose the following sequence is executed:

> The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail, and the command is added to the environment's history.

In doing so, several calls will be made in both the internal and external APIs of View and Model. Here is a well-commented piece of example code (without specific implementation) that shows how the API flow is structured:

```java
/**
 * Executed by View's internal API, will get the user input text from the console
 * @return the dialog currently in the console. Done within the UserCommands class.
 */
protected String consoleInput();

/**
 * Add the command to the environment's history. This is done in the View's internal API.
 * The command is added to an instance variable representing the most recently
 * executed commands. Done within the UserCommands class.
 */
protected void addToHistory();

/** 
 * The Model interacts with the View here, getting the String that was input to the console.
 * This method is contained in Model's external API. Done within the Interpreter class.
 * @return the dialog that was input into the console
 */
public String getInput();

/**
 * Inside of the Model (internal API), the interpreter parses the input that it got from the user
 * and returns a Command object. Done in the Interpreter class.
 * @param input what the user typed into the console
 * @return a Command object that represents what the user input
 */
public Command parse(String input);

/**
 * Inside of the Model (internal API), uses the command parsed above to update all properties of the turtle.
 * However, since the turtle is contained in the view, this just returns a Properties object representing new properties of the  
 * turtle. Done in the Interpreter class.
 * @param command the command generated by parsing
 * @return a Properties object representing the updated properties of the turtle
 */
protected Properties generateTurtleProperties(Command command);

/**
 * Inside of View's external API, updates the position of the Turtle. It must get this information from the Properties object
 * generated by the above method. Done within the Turtle class.
 * @param newProperties the new properties generated by the above method.
 */
public void updateTurtle(Properties newProperties);

/**
 * Finally, the turtle is drawn within the View's internal API. This includes drawing the line. The method updates a turtle object 
 * that the GUI class holds.
 */
protected void updateTurtleView();
```

### View - User Interface

User wants to choose a different image for the turtle:

```java
/**
* Executed by View’s internal API, will get new image that user just uploaded to the program
*/
private File getImage();

/**
* Executed by View’s internal API, will add uploaded image file to the drop-down menu that allows users to choose an image for the turtle
*/
private void addImage(File image);

/**
* Executed by View’s internal API, will set turtle to the new image uploaded and chosen by user
*/
private void setImage(File image);
```

User wants to choose a different language for the commands:

```java
	/**
	* Executed in View’s external API, sets properties file that the Commands class uses 
	*/
	public void setLanguagePropertiesFile(String language);
```

User types fd 50 when the turtle is near the edge and it appears on the other side:
```java
/**
 * Executed by View's internal API, will get the user input text from the console
 * @return the dialog currently in the console. Done within the UserCommands class.
 */
protected String consoleInput();

/**
 * Add the command to the environment's history. This is done in the View's internal API.
 * The command is added to an instance variable representing the most recently
 * executed commands. Done within the UserCommands class.
 */
protected void addToHistory();

/** 
 * The Model interacts with the View here, getting the String that was input to the console.
 * This method is contained in Model's external API. Done within the Interpreter class.
 * @return the dialog that was input into the console
 */
public String getInput();

/**
 * Inside of the Model (internal API), the interpreter parses the input that it got from the user
 * and returns a Command object. Done in the Interpreter class.
 * @param input what the user typed into the console
 * @return a Command object that represents what the user input
 */
public Command parse(String input);

/**
 *inside the external view API, the view section is asked for its coordinates 
 * and returns the current size of the frame in which the turtle occupies
 *  and the command is generated to move the turtle in a toroidal fashion
 */
public int getWindowSize();


/**
 * Inside of the Model (internal API), uses the command parsed above to update all properties of the turtle.
 * However, since the turtle is contained in the view, this just returns a Properties object 
 * representing new properties of the  
 * turtle. Done in the Interpreter class.
 * @param command the command generated by parsing
 * @return a Properties object representing the updated properties of the turtle
 */
protected Properties generateTurtleProperties(Command command);

/**
 * Inside of View's external API, updates the position of the Turtle. It must get this information from the Properties object
 * generated by the above method. Done within the Turtle class.
 * @param newProperties the new properties generated by the above method.
 * @param turtle the back-end turtle being updated
 */
public void updateTurtle(Properties newProperties, BETurtle turtle);
```
User wants to redo a certain command:
```java
/**
 * Executed by View's internal API, when a key is pressed it will assess the value of the 
 * key and call correct methods as defined by the architecture (in this case the key is up arrow 
 * and cycleHistory() is called
 */
private void cycleHistory();

/**
 * Executed by View's internal API, when pressed the UI will cycle through a list
 * of the history of string commands entered as created in addHistory, clear the textbox and 
 * add the command from the list to the form 
 */
private void cycleHistory();
```


### Model - Interpreter

1. User types in a poorly formatted command and an error is thrown to let the user know

```java
/** 
 * The Model’s external API gets the String input from the console.
 * This method is contained in Model's external API. Done within the Interpreter class.
 * @return the dialog that was input into the console
 */
public String getInput();

/**
 * Inside of the Model (internal API), the interpreter parses the input that it got from the user
 * and returns a Command object, the command object will not be returned and instead an error 
 * will be thrown because the method will not recognize the poorly formatted command. Done in 
 * the Interpreter class.
 * @param input what the user typed into the console
 * @return a Command object that represents what the user input
 */
public Command parse(String input);

/**
 * Inside of the Model (internal API), in the SLogoException class the parse method will throw an 
 * error to alert the user that the command could not be parsed as the input was not formatted 
 * properly.
 */
public SLogoException(Throwable problem);
```
2. The user inputs ‘fd 50 fd 50 fd 50’ and the interpreter grabs that input to use in the parsing.

```java
/**
 * The Model's external API grabs the input from the View.
 * @return the user input from the console
 */
public String getUserInput() {
	return View.userInput;
}
/**
 * Inside of the Model (internal API), the interpreter parses the input that it got from the user
 * and returns a Command object.
 * @param input what the user typed into the console
 * @return a Command object that represents what the user input
 */
public Command parse(String input) {
	// parsing implementation here
}
```
1. Get the result of parsing a user input, and put this into command object.
```java
/**
 * Command objects must be specified in a properties file set up at initialization.
 * @param input received from the external API of View
 * @return a Command object representing the input according to the properties file
 */
public Command parse(String input) {
	Command retCommand = new Command();
	// parsing is done here, but is too complicated to include currently
	return retCommand;
}
```
2. Update the back-end turtle according to a new `Properties` object, specifying the turtle's x-coordinate, y-coordinate, orientation (in degrees), and picture.
```java
/**
 * The Properties object is generated by the Command object. This object
 * specifies new properties of the turtle, and this method sets them accordingly.
 * This is done in the back-end turtle class.
 */
 public void updateTurtle(Properties newProperties, BETurtle turtle) {
	 turtle.setLocation(newProperties.getXCoord(), newProperties.getYCoord());
	 turtle.setOrientation(newProperties.getOrientation());
	 // only do this if image is different than before
	 if (!turtle.getImage().equals(newProperties.image()) {
		 // set new image
	}
}
```

### Design Considerations
As a team, we had a long discussion about whether there needs to be two turtles or only one. If two, one would exist in the back-end, and would be solely responsible for updating state variables, such as position, orientation, associated picture, etc. The other one would exist in the front end, and it would receive information about its state from the back-end turtle. The front-end turtle's only takes responsibility for drawing itself according to its new state (i.e. handles the JavaFX code).  We originally liked this idea because it keeps API implementation separate - if one wanted to change how the back-end turtle processes commands, this wouldn't change how the front-end turtle draws itself. However, we also feel like we are creating a data dependency here, and that having two turtles is redundant. Likely, we will stick with the two separate turtles, but we will weight the benefits and consequences more first.

As it stands, a large question mark surrounds the parser itself. We do not know whether the raw input will be fed into the parser right away, or if the View will first do some sort of parsing to turn the raw input into something that the interpreter can understand. The advantage of feeding it in as raw input is that we keep API implementation separate - changes to how the interpreter works will not affect the way that the View simply feeds in raw input. However, if we put some elementary parsing on the front end, we can check for errors without having to first communicate with the Model - this reduces dependencies. The View could then check for and report errors before passing the input into the interpreter. This, of course, eliminates API flexibility.

We struggled with thinking about how to tell the back-end turtle how to change its state. First, we considered hard-coding a bunch of `if`-`else` statements into the interpreter itself, and constructing a `Method` object from the the user input. That object would then be passed to the back-end turtle, which would always call `Method.invoke()` with itself (`this`) as an argument; theoretically, this could change its state very easily. This would make adjusting the interpreter and its accepted commands much easier. However, this proves slightly unnecessary if all the back-end turtle holds is several state variables. Then came our second idea - to have each Command object (for each separate command that the user could type in) call one of the turtle's methods (like `updatePosition()` or `updateOrientation()`, etc.) This creates quite a few command objects (at least 49), which is not only tedious but produces clutter. No matter what, we figure that we have to hard-code some `if`-`else` statements somewhere, but our primary goal is modularity, so we are leaning toward command objects. However, we also consider creating one master `Command` class with different methods pertaining to different user inputs - however, this limits modularity as well.


### Team Responsibilities 

Hemanth - On the View team, will primarily be responsible for creating the user interface

Conrad - On the View team, I will be working with Hemanth to create the user interface and add features pertaining to displaying previous commands and the potential list as well as how to use the language, and I will be working with the backend team to facilitate the exchange of information between the two halves of the project.

Alexi - I will be working on the parsing of the user input as well as the interpreter on the back-end.

Dylan - I will work on the Command objects (whether subclasses or methods) and the back-end turtle updating, including the `Properties` object and how it relates to properties of the back-end turtle. Will deal with communicating this information to the front end.


