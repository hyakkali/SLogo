package userinterface;

import backend.SLogoModel;
import turtle.Turtle;

/*
        Author @ Conrad -- defines the commmands that are public to the backend of slogo project
 */
public interface UserInterface
{
    //sets the image of the turtles called in loading
    public  abstract void setTurtleImage(String image);

    //set the background color of the form called un
    public abstract void setBackgroundColor(String value);

    //set the language of the form
    public abstract void setLanguage(String s);

    //set the number of turtles called by load
    public abstract void setTurtleNum(int a);

    //clears the lines of the form called by console commands
    public abstract void clearLines();

    //resets the form called by console commands
    public abstract void reset();

    //sets the language called by console coommand
    public abstract void setLanguage(double d);

    //set the turtle image from console command
    public abstract void setTurtleImage(double d);

    //sets the background of te form based on console command
    public abstract void setBackGroundColor(double d);

    //sets the pen color od all turtles based on console command
    public abstract void setPenColor(double d);

    //prints error called by load and in variablelist
    public abstract void printError(String s);

    //add ths methods necessary to UI fromm slogomodel called on init
    public abstract void initializeBackend(SLogoModel s);


}
