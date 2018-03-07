package userinterface;

import javafx.scene.shape.Line;
import pen.LinePen;
import pen.Pen;
import turtle.Turtle;

import java.util.*;

public class State {

    public List<Line> drawnLines;
    public List<Turtle> onScreen;
    public String background;
    public String language;

    public State(List<Turtle> turtles, List<Line> lines, String bStyle, String lang)
    {
        background=bStyle;
        drawnLines = new ArrayList<Line>();
        copyLines(lines);

        onScreen = new ArrayList<Turtle>();

        for(Turtle l : turtles)
        {
            Turtle clone = l.clone();
            onScreen.add(clone);
        }
        language = lang;
    }

    public int hashCode()
    {
        double hash=1;

        for(Turtle turtle :onScreen)
            hash+= turtle.getID()*turtle.getX()/turtle.getY()+turtle.getRotate()+(((double)turtle.getImage().hashCode())/90000);
        for(Line line : drawnLines)
            hash-=line.getEndX()+2*line.getEndY()-line.getEndX()*line.getEndY()/2;
        hash+=(background.hashCode());
        hash+=language.hashCode();
        int h=(int)hash;
        System.out.println(h);
        return h;
    }

    private void copyLines(List<Line> lines)
    {
        for(Line l : lines) {
            Line copy = new Line(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
            copy.setFill(l.getFill());
            drawnLines.add(copy);
        }
    }

    public boolean equals(State other)
    {
        return (other!=null && this.hashCode()==other.hashCode());
    }





}
