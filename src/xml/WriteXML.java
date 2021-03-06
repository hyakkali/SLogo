package xml;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XML11Serializer;
import javafx.scene.shape.Line;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import turtle.Turtle;
import userinterface.State;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.IllegalFormatException;
import java.util.List;

public class WriteXML {

    private static DocumentBuilderFactory documentBuilderFactory;
    private static DocumentBuilder documentBuilder;
    private static Document xmlDoc;
    private static Element rootElement;
    private static final String STATE_FILE = "-state";
    private static final String PREF_FILE = "-pref";
/*
      Author @ Conrad defines how and where files are saveed by the user
 */

    /*
        builds the structure of the file writer
     */
    private static void buildDoc() {
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            xmlDoc = documentBuilder.newDocument();
            rootElement = xmlDoc.createElement("Properties");
            xmlDoc.appendChild(rootElement);
        }
        catch(javax.xml.parsers.ParserConfigurationException e){}
    }

    //calls save turtles lines and preferences and writes output
    public static void saveFile(State state) throws Exception{

        buildDoc();
        writePreferences(state.language, state.background, state.pastTurtles.size());

        writeTurtles(state.pastTurtles);
        writeLines(state.pastLines);

        createFile(STATE_FILE);

    }

    //saves preferences and outputs to directory od data
    public static void savePref(String background, String language, int tNum) throws Exception {
        buildDoc();
        writePreferences(language, background, tNum);
        createFile(PREF_FILE);
    }

    private static void writeTurtles(List<Turtle> turtles) {
        for(Turtle turtle  :turtles)
        {
            Element turtleElement = xmlDoc.createElement("Turtle");
            turtleElement.appendChild(xmlDoc.createTextNode(turtle.toString()));
            rootElement.appendChild(turtleElement);
        }
    }

    private static void writeLines(List<Line> lines) {
        for (Line line : lines) {
            Element lineElement = xmlDoc.createElement("Line");
            String lInfo = "";
            lInfo += line.getStartX() + " " + (line.getStartY()) + " " + line.getEndX() + " " + line.getEndY() + " " + line.getStroke().toString() + " " + line.getStrokeWidth();
            lineElement.appendChild(xmlDoc.createTextNode(lInfo));
            rootElement.appendChild(lineElement);
        }
    }

    //specifies a format and file location to save the information
    private static void createFile(String fileType) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        File xmlFile = new File(System.getProperty("user.dir")+"/data/saved/"+dtf.format(now) + fileType + ".xml");
        OutputFormat out = new OutputFormat();
        out.setIndent(5);
            FileOutputStream fos = new FileOutputStream(xmlFile);
            XML11Serializer serializer = new XML11Serializer(fos, out);
            serializer.serialize(xmlDoc);

    }

    private static void writePreferences(String language, String background, int tNum) {

        Element turtleNum = xmlDoc.createElement("NumTurtles");
        turtleNum.appendChild(xmlDoc.createTextNode(String.valueOf(tNum)));
        rootElement.appendChild(turtleNum);

        Element backgroundElement = xmlDoc.createElement("Background");
        backgroundElement.appendChild(xmlDoc.createTextNode(String.valueOf(background)));
        System.out.print(background);
        rootElement.appendChild(backgroundElement);

        Element languageElement = xmlDoc.createElement("Language");
        languageElement.appendChild(xmlDoc.createTextNode(String.valueOf(language)));
        rootElement.appendChild(languageElement);
    }

}

