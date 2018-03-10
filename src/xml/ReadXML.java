package xml;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XML11Serializer;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLLabelElement;
import turtle.Turtle;
import userinterface.State;
import userinterface.UserScreen;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadXML {

    public static State buildState(File xml) {
        if(!xml.getName().contains("-state"))
            throw new IllegalStateException();;
           DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
           try {
               DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                       Document document = documentBuilder.parse(xml);

                       List<Turtle> turtles = new ArrayList<>();
                       List<Line> lines = new ArrayList<>();
                       Element root = (Element)document.getElementsByTagName("Properties").item(0);
                       if(root==null)
                            throw new IllegalStateException();
                       NodeList turtleInfo = root.getElementsByTagName("Turtle");
                       for (int a = 0; a < turtleInfo.getLength(); a++)
                           turtles.add(new Turtle(((Element)turtleInfo.item(a)).getTextContent()));

                       NodeList lineInfo = root.getElementsByTagName("Line");
                       for (int a = 0; a < lineInfo.getLength(); a++)
                           lines.add(buildLine(((Element)lineInfo.item(a)).getTextContent()));

                       String background = ((Element)root.getElementsByTagName("Background").item(0)).getTextContent();
                       String langugae = ((Element)root.getElementsByTagName("Language").item(0)).getTextContent();
                       State loadState = new State(turtles, lines, background, langugae);
                       return loadState;
           }
           catch(Exception e){throw new IllegalStateException();}
    }

    public static void buildPreference(File xml, UserScreen view)
    {
        if(!xml.getName().contains("-pref"))
            throw new IllegalStateException();;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xml);

            Element root = (Element)document.getElementsByTagName("Properties").item(0);
            if(root==null)
                throw new IllegalStateException();
            view.setBackgroundColor(((Element)root.getElementsByTagName("Background").item(0)).getTextContent());
            view.setLanguage(((Element)root.getElementsByTagName("Language").item(0)).getTextContent());
            view.setTurtleNum(Integer.valueOf(((Element)root.getElementsByTagName("NumTurtles").item(0)).getTextContent()));
            }
        catch(Exception e){throw new IllegalStateException();}
    }

    private static Line buildLine(String lInfo) {
        Line l = new Line();
        l.setStartX(Double.valueOf(lInfo.substring(0,lInfo.indexOf(" "))));
        lInfo=lInfo.substring(lInfo.indexOf(" ")+1);
        l.setStartY(Double.valueOf(lInfo.substring(0,lInfo.indexOf(" "))));
        lInfo=lInfo.substring(lInfo.indexOf(" ")+1);
        l.setEndX(Double.valueOf(lInfo.substring(0,lInfo.indexOf(" "))));
        lInfo=lInfo.substring(lInfo.indexOf(" ")+1);
        l.setEndY(Double.valueOf(lInfo.substring(0,lInfo.indexOf(" "))));
        lInfo=lInfo.substring(lInfo.indexOf(" ")+1);
        l.setStroke(Paint.valueOf(lInfo.substring(0,lInfo.indexOf(" "))));
        lInfo=lInfo.substring(lInfo.indexOf(" ")+1);
        l.setStrokeWidth(Double.valueOf(lInfo));
        return l;
    }


}
