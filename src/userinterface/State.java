package userinterface;

import javafx.scene.shape.Line;
import turtle.Turtle;

import java.util.*;

public class State {
    /*Autohr @Conrad saves the state of all elements in the screen
        and allows the UI to reinitialize when undo is pressed

 */
    public List<Line> pastLines;
    public List<Turtle> pastTurtles;
    public String background;
    public String language;

    /*
        creates the previous state from the info from UI
     */
    public State(List<Turtle> turtles, List<Line> lines, String bStyle, String lang) {
        background=bStyle;
        pastLines = new ArrayList<Line>();
        copyLines(lines);

        pastTurtles = new ArrayList<Turtle>();
        copyTurtles(turtles);

        language = lang;
    }

    /*
        create random hash code for eachstate
     */
    @Override
    public int hashCode()
    {
        double hash=1;

        for(Turtle turtle :pastTurtles)
            hash+= turtle.getID()*turtle.getX()/turtle.getY()+turtle.getRotate()+(((double)turtle.getImage().hashCode())/90000);
        for(Line line : pastLines) {
            hash -= line.getEndX() * line.getEndY() - line.getEndX() * line.getEndY() ;
            hash-=line.getStroke().hashCode()/90000;
        }
        hash+=(background.hashCode());
        hash+=language.hashCode();
        return ((int) hash);
    }

    /*
        copies lines from the input state
     */
    private void copyLines(List<Line> lines)
    {
        for(Line l : lines) {
            Line copy = new Line(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
            copy.setStroke(l.getStroke());
            pastLines.add(copy);
        }
    }

    /*
        invokes a copy method that returns a copy of the turtles from UI
     */
    private void copyTurtles(List<Turtle> turtles) {
        for(Turtle l : turtles)
        {
            Turtle clone = l.clone();
            pastTurtles.add(clone);
        }
    }

    //returns if two states are the sam for userscreen to decide to add a new states
    public boolean equals(State other)
    {
        return (other!=null && this.hashCode()==other.hashCode());
    }





}
