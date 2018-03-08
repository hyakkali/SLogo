package userinterface;


import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XML11Serializer;
import javafx.scene.shape.Line;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import turtle.Turtle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class WritePreferences {


    public static void saveForm(String background, String language, List<Turtle> turtles, List<Line> lines ) throws Exception{
        DocumentBuilderFactory documentBuilderFactory= DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder =documentBuilderFactory.newDocumentBuilder();
        Document xmlDoc = documentBuilder.newDocument();


        Element rootElement = xmlDoc.createElement("Preferences");
        Element turtleNum =xmlDoc.createElement("Turtle Num");
        turtleNum.appendChild(xmlDoc.createTextNode(String.valueOf(turtles.size())));
        rootElement.appendChild(turtleNum);
        Element turtlesElement =xmlDoc.createElement("Turtles");
        for(Turtle turtle  : turtles)
        {
            Element turtleElement = xmlDoc.createElement("Turtle");
            String tInfo="";
            tInfo+=turtle.getID()+ " " + turtle.getX() + " " +(turtle.getY())+ " "+ turtle.getRotate()+ " "+ turtle.getImageURL()+ " " +turtle.getActive()+ " " +turtle.isVisible();
            turtleElement.appendChild(xmlDoc.createTextNode(tInfo));
            turtlesElement.appendChild(turtleElement);
        }
        rootElement.appendChild(turtlesElement);

        Element linesElement =xmlDoc.createElement("Lines");
        for(Line line  : lines)
        {
            Element lineElement = xmlDoc.createElement("Line");
            String lInfo="";
            lInfo+=line.getStartX() + " " +(line.getStartY())+ " "+ line.getEndX()+ " "+ line.getEndY()+ " " +line.getFill().toString()+ " " +line.getStrokeWidth();
            lineElement.appendChild(xmlDoc.createTextNode(lInfo));
            turtlesElement.appendChild(lineElement);
        }
        rootElement.appendChild(linesElement);

        Element backgroundElement = xmlDoc.createElement("Background");
        backgroundElement.appendChild(xmlDoc.createTextNode(String.valueOf(background)));
        rootElement.appendChild(backgroundElement);

        Element languageElement = xmlDoc.createElement("Language");
        languageElement.appendChild(xmlDoc.createTextNode(String.valueOf(language)));
        rootElement.appendChild(languageElement);

        xmlDoc.appendChild(rootElement);
        File xmlFile = new File("test.xml");
        OutputFormat out= new OutputFormat();
        out.setIndent(5);
        FileOutputStream fos= new FileOutputStream(xmlFile);
        XML11Serializer serializer = new XML11Serializer(fos,out);
        serializer.serialize(xmlDoc);

    }


}
