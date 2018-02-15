Cell Society APIs
========
### Analyzing team 14

* **Reader**: 
	* `public void saveXML(Grid myGrid);` should **not** be a public method, because it uses another object, and the method would fit better in that object.
	* The getter methods should be part of the _external_ API because other classes, such as `GameType`, require information about the state of the simulations.
### Six APIs
1. **Internal Simulation**: Each cell needs to be able to get and check its neighbors.
2. **External Simulation**: The simulation must communicate with the visualization with some sort of `update()` method that figures out the new states of cells and draws them. It also needs to be able to draw the cells upon initialization. It needs to be able to communicate with the configuration package so that it knows the initial states, simulation type, cell type, etc. It also needs to be able to communicate the current states of all cells for purposes of saving XML files and updating the GUI.
3. **Internal Configuration**: the internal configuration needs to have methods for both parsing and saving XML files. 
4. **External Configuration**: The configuration package needs to be able to send the data to the simulation. If it wants to save the XML file, it needs to have a method to get the data (the current state of all cells) from the simulation.
5. **Internal Visualization**: It needs to have methods to position all GUI components like buttons, sliders, combo boxes, etc. The method for doing the _actual drawing_ of the grid is also contained here.
6. **Internal Visualization**: This API requires a method for initializing GUI components that need to interact with other parts of the simulation. Additionally, it needs to receive data about the current state of the simulation, so that it can pass that data to the encapsulated internal API. 
	