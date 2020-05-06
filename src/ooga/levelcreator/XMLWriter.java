package ooga.levelcreator;

import javafx.util.Pair;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLWriter {
    private Document doc;
    private Map<String, List<Pair<Integer,Integer>>> map;
    public XMLWriter() throws ParserConfigurationException {
        doc = createDoc();
        this.map = new HashMap<>();
    }

    private Document createDoc() throws ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        return dBuilder.newDocument();
    }

    public void createXML(Map<String, List<Pair<Integer,Integer>>> map, String backgroundPath, String gameName){
        try {
            this.map = map;
            Element rootElement = doc.createElement("Level");
            doc.appendChild(rootElement);
            createBackgroundTag(rootElement, backgroundPath);
            for (String str: map.keySet()){
                for (Pair<Integer,Integer> p : map.get(str)){
                    int x = p.getKey();
                    int y = p.getValue();
                    createCell(rootElement, str,x+"",""+y);
                }
            }
            writeXML(String.format("data/Games/%s/level_1.xml", gameName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void createBackgroundTag(Element rootElement, String backgroundPath) {
        Element background = doc.createElement("BackgroundImage");
        background.appendChild(doc.createTextNode(backgroundPath));
        rootElement.appendChild(background);
    }

    private void createCell(Element rootElement, String name, String x, String y) {
        Element cell = doc.createElement("Cell");
        rootElement.appendChild(cell);
        createObject(cell, name,x,y);
    }

    private void writeXML(String path) throws TransformerException {
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(path));
        transformer.transform(source, result);

        // Output to console for testing
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }
    private void createObject(Element cell, String name, String x, String y) {
        //Object element - name
        Element object =doc.createElement("Object");
        object.appendChild(doc.createTextNode(name));
        cell.appendChild(object);

        //Object element - x
        Element xCor =doc.createElement("X");
        xCor.appendChild(doc.createTextNode(x));
        cell.appendChild(xCor);

        //Object element - y
        Element yCor =doc.createElement("Y");
        yCor.appendChild(doc.createTextNode(y));
        cell.appendChild(yCor);
    }

    private Attr getAttr(String attribute, String value) {
        Attr attr = doc.createAttribute(attribute);
        attr.setValue(value);
        return attr;
    }
}
