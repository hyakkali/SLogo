package userinterface;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XML11Serializer;
import javafx.scene.shape.Line;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLLabelElement;
import turtle.Turtle;

import javax.swing.event.DocumentEvent;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WritePreferences {

    private static DocumentBuilderFactory documentBuilderFactory;
    private static DocumentBuilder documentBuilder;
    private static Document xmlDoc;
    private static Element rootElement;
    private static final String STATE_FILE = "-state";
    private static final String PREF_FILE = "-pref";

    private static void buildDoc() {
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            xmlDoc = documentBuilder.newDocument();
            rootElement = xmlDoc.createElement("Properties");
            xmlDoc.appendChild(rootElement);
        }
        catch(javax.xml.parsers.ParserConfigurationException e){System.out.println("WRONGDOC");}
    }

    public static void saveFile(String background, String language, List<Turtle> turtles, List<Line> lines) {

        buildDoc();
        writePreferences(language, background, turtles.size());

        writeTurtles(turtles);
        writeLines(lines);

        writeFile(STATE_FILE);
    }

    public static void savePref(String background, String language, int tNum) {
        buildDoc();
        writePreferences(language, background, tNum);
        writeFile(PREF_FILE);
    }

    private static void writeTurtles(List<Turtle> turtles) {
        Element turtlesElement = xmlDoc.createElement("Turtles");
        for(Turtle turtle  :turtles)
        {
            Element turtleElement = xmlDoc.createElement("Turtle");
            String tInfo = "";
            tInfo += turtle.getID() + " " + turtle.getX() + " " + (turtle.getY()) + " " + turtle.getRotate() + " " + turtle.getImageIndex() + " " + turtle.getActive() + " " + turtle.isVisible();
            turtleElement.appendChild(xmlDoc.createTextNode(tInfo));
            turtlesElement.appendChild(turtleElement);
        }
                rootElement.appendChild(turtlesElement);
    }

    private static void writeLines(List<Line> lines) {
        Element linesElement = xmlDoc.createElement("Lines");
        for (Line line : lines) {
            Element lineElement = xmlDoc.createElement("Line");
            String lInfo = "";
            lInfo += line.getStartX() + " " + (line.getStartY()) + " " + line.getEndX() + " " + line.getEndY() + " " + line.getStroke().toString() + " " + line.getStrokeWidth();
            lineElement.appendChild(xmlDoc.createTextNode(lInfo));
            linesElement.appendChild(lineElement);
        }
        rootElement.appendChild(linesElement);
    }

    private static void writeFile(String fileType) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.print(dtf.format(now));
        File xmlFile = new File(dtf.format(now) + fileType + ".xml");
        OutputFormat out = new OutputFormat();
        out.setIndent(5);
        try {
            FileOutputStream fos = new FileOutputStream(xmlFile);
            XML11Serializer serializer = new XML11Serializer(fos, out);
            try {
                serializer.serialize(xmlDoc);
            } catch (java.io.IOException e) {
                System.out.print("WRONGt1");
            }
        } catch (FileNotFoundException e) {
            System.out.println("WRONGt2");
        }
    }

    private static void writePreferences(String language, String background, int tNum) {

        Element preferences  = xmlDoc.createElement("Preferences");
        Element turtleNum = xmlDoc.createElement("NumTurtles");
        turtleNum.appendChild(xmlDoc.createTextNode(String.valueOf(tNum)));
        preferences.appendChild(turtleNum);

        Element backgroundElement = xmlDoc.createElement("Background");
        backgroundElement.appendChild(xmlDoc.createTextNode(String.valueOf(background)));
        preferences.appendChild(backgroundElement);

        Element languageElement = xmlDoc.createElement("Language");
        languageElement.appendChild(xmlDoc.createTextNode(String.valueOf(language)));
        preferences.appendChild(languageElement);
        rootElement.appendChild(preferences);
    }

}
