SLogo API Review ddp19, jql3
=====
## Part 1
* If a programmer wishes to add a new command, then all that they have to do is add a new command instance that implements the `Command` interface. Then, since the `Command` interface has a required method called `execute()` (or something similar), the `generateTurtleProperties()` command can just invoke that method and generate new properties from the turtle. As long as the new command implements the `Command` interface, everything will work out fine.
* We decided to separate the front-end turtle from the back-end turtle - in order to do this, we need to have the front end turtle only capable of drawing and the back-end capable of generating new properties. Then, the back-end just calls the View's `updateTurtle()` method with the new properties of the turtle, and the View updates the turtle. This gives extra support for adding new turtles.
* The API encapsulates the implementation decisions because only the input and output matter - nothing in between does, due to the API.
* The parsing and interpretation is done on the Model side, so the Model needs to determine if the input from the user represents a valid command. If not, it will create a new `Alert` and the the View will have a public method to display that alert. 
* Our API design is "good" because it is flexible. To me, "good" APIs allow for maintainability - in the future, if a different programmer wanted to add/change a feature, then they should be able to do so quite easily. On the contrary, "bad" API design produces monolithic code, meaning code that is only intended to function in one specific way. This makes future maintenance that much harder. Our "good" API design means that somebody could theoretically design our program in a different way, and it would still be functional.

## Part 2
* Our `Command` interface utilizes a factory design pattern - it creates a command-type object without specifying exactly what the implementation of the specific object entails. This provides for more flexibility.
* I am most excited to work with designing and implementing the `Command` interface
* I am worried about communication between the Model and the View, and ensuring that we keep certain implementation details encapsulated.
* **Use Cases**:
 1. Create a `Command` Factory based upon a `.properties` file. 
 2. Execute a `Command` object.
 3. Drawing a new turtle from the View. 
 4. Generating a new `Properties` object from the command.
 5. Updating the back-end turtle with new properties.

