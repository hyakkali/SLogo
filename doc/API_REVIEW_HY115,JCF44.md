## API Review 

### Part 1

1. The drawing and graphics is all done on the frontend but relies on the data from the backend. This is flexible because it allows for more independence between the frontend and the backend. 
2. The API/Design encapsulates our implementation decisions because the frontend makes the calculations to display the turtle and draw the lines instead of the backend doing all the work.
3. Any errors that may result will be processed by the backend such as improper commands or an error in uploaded code files but these errors will be displayed on the frontend. Having a frontend API method that 
gets an error from the backend will allow the frontend to display the error. 
4. Our API/Design is good because everything flows in one direction (frontend to backend to frontend). We also tried to decrease the amount of external API's we use and do most of the work using internal API's so that 
we can reduce the reliance between frontend and backend as much as possible. I believe a good API is one that can be run without much dependencies. 

### Part 2

1. One design pattern that is currently implemented is the MVC (model view controller) where the model is the backend and the view is the frontend/UI. 
2. Most excited to work with the backend on implementing the turtle and drawing the lines. 
3. Most worried about relying too much on the backend such that progress slows down because we aren't able to work together often. Worried that the smallest changes will throw a large amount of errors. 
4. Use Cases:
	1. Display valid command on the screen
	2. Draw a straight line with the turtle
	3. Display error if command contains an error
	4. Change language of the commands
	5. Changing the background color