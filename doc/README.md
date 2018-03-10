# slogo

CompSci 308 slogo Project

Collaborators: Conrad Mitchell, Alexi Kontos, Hemmanth Yekkali, Dylan Powers
Start Date: 2/13/18
End Date: 3/10/18
Estimated Number of Hours Worked (each): 50+ each

### Roles

- Alexi
	- worked backend and created the parser and worked on executer

- Dylan
	-worked backend and created the commands and helped with executer/ pasrser

- Conrad
	- worked frontend and designed most of UserInterface

-Hemmanth
    -worked frontend and designed most of controller model and turtle/pen

### Resources Used

- Stack overflow as well as Duvalls website and examlpe specifically advanced_lab were used

### Notable Files

- To Start Project
	- Use Main.Java

- To Test Project
	-use play around in the turtle window

## OverView

We began this project by outlining the interactions between the front end and backend by defining the assumptions and dependencies of the APIs for each side of the project. Going forward we worked independently to implement our respective ends meeting in large group to resolve any integration conflicts. During this process, the complexity of the interactions between the front and back became clear and we were forced to move the UI into the backend. As development contiinued, we managed to resolve this error in organization and continut to implement new features. The backend encountered issues parsing the more complicated commands using the stack structure of decoding commands, and another major refactor was done. In the future, we would probably spend more time planning to avoid going back on design decision and losing time

### Error-Handling

- We expect this project to be able to handle the kinds of errors caused by improper commands in the console
	- passing incorrrect paramters
	-calling commands that dont exist
	- in loading files preventing the user from picking bad files

- for the most part we accounted for these errors. inputing incorrect commands will cause the program to crash

### Assumptions and Simplifications

-at the moment we have simplified the load file feature to only work on turtles lines language background and it does not currently yield saving of variables and commands. additinally, we are operating under the assumption that the commands entered will be valid slogo commands

### Bugs

Major Bugs:

- the turtle goes off the screen
- invalid commands cause errors
-comboboxes dont update when some values change
### Features

- the user has the ability to move the turtle and work in the environment and work with several turtles. all turtles can be edited individually graphically: right clicking yields information about each and double clicking sets active/inactive one click defines that turtle as active for moving with asdw keys. colors of the background can be changed as well as the language and line color of turtles which are all displayed in the UI. animate and undo are integrated as well..use alt to undo redo
- the preferences(background, language, and turtle number can be saved as a prefeerence xml, and the turtles, lines background, and language can be saved as a copy of a state and loaded dynamically. Also, the variables are displayed and graphically editable in the UI and the pen can be changed dynamically as well. finally new workspaces can be created dynamically and instantiated to saved xml states/ preferences
