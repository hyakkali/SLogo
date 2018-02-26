# SLogo API Review; cnd11, cmm134

### Part 1

1. What about your API/design is intended to be flexible?
	* adding a new command should be easy with the use of the interpreter parser and factory design scheme. The ability to create new ones and maintain an interface so that each command acts as a black box capable of being invoked in a standaried way allows these new comands to easily incorporate into the porjcted
2. How is your API/design encapsulating your implementation decisions?
	* most of this project is encapsulated, and the front end speecifically will be virtually inaccessable to the backend except through the controlled class. This way the backend can only update and no
3. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
	* error cases for incorrect commands will be thrown. 
4. Why do you think your API/design is good (also define what your measure of good is)?
	*our api is good because it maximizes the flexibility of the project, the separation of the two ends makes it easy to change one without the other

### Part 2

1. How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
	*we are currently implementing a factory design scheme to allow commands to be used without knowing which command they are. 
2. What feature/design problem are you most excited to work on?
	* i ma most excited to work with my team on the creation of the command tree/ compiler. It seems like that will be an algorithmically complex problem that requires a working together and a creative solution  
3. What feature/design problem are you most worried about working on?
	* I am most worried about the controller that interfaces the back and front ends because it seems simple, and I know that nothing in this class is simple. I am not lookinf forward to the surprises that this portion of the porjct will throw 
4. Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
	* user wants to create a new command and then see that command appear in the directory of commands
	* the user wants to cycle through past commands to redo one of them 
	* the user wants to clear the form 
	* the user wants to save code that was written for later use
	* the user wants to change the size of the form 