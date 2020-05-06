package ooga.parser.components;

import ooga.parser.validators.XMLValidator;
import ooga.utilities.exception.ParserException;
import ooga.utilities.exception.XMLException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * The purpose of this class is to consolidate common parser components into a single class, for the purpose of inheritance
 * @author Alex Xu
 */

public abstract class AbstractParser {
    private Document myDocument;
    private Element myRootElement;

    /**
     * Constructor for an abstract parser
     * @param fileName
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public AbstractParser(String fileName, String xsd){
        if(XMLValidator.validateXMLStructure(fileName, xsd)){
            File file = new File(fileName);
            myDocument = documentSetup(file);
            myRootElement = extractRoot();
        }
    }

    /**
     * Extracts the element's text field
     * @param tag
     * @param parentElement
     * @return
     */
    protected String extractElementValue(String tag, Element parentElement){
        return getChildNode(tag, parentElement).getTextContent();
    }

    /**
     * Overloaded method, assumes extracting value from a child of the root.
     * @param tag
     * @return
     */
    protected String extractElementValue(String tag){
        return extractElementValue(tag, getRootElement());
    }

    /**
     * Extracts a NodeElement from a NodeList at the designated Index.
     * @param nodeList
     * @param index
     * @return
     */
    protected Element extractNodeElementFromList(NodeList nodeList, int index){
        Node node = nodeList.item(index);
        return convertNodetoElement(node);
    }

    /**
     * Extracts a sub-element from the root with the name of the tag
     * @param nodeName
     * @return
     */
    protected Element extractNodeElementFromRoot(String nodeName){
        Node node = getChildNode(nodeName, getRootElement());
        return convertNodetoElement(node);
    }

    /**
     * Extracts a sub-element from the parent with the name.
     * @param nodeName
     * @param parentElement
     * @return
     */
    protected Element extractNodeElementFromParent(String nodeName, Element parentElement){
        Node node = getChildNode(nodeName, parentElement);
        return convertNodetoElement(node);
    }

    /**
     * Extracts a NodeList from the parent with the same name of the tag
     * @param tag
     * @param parentElement
     * @return
     */
    protected NodeList getNodeList(String tag, Element parentElement){
        return parentElement.getElementsByTagName(tag);
    }

    /**
     * Overloaded method that extracts a Nodelist from the root of the document.
     * @param tag
     * @return
     */
    protected NodeList getNodeList(String tag){
        return getNodeList(tag, getRootElement());
    }

    /**
     * Returns splitted input.
     * @param currentElement
     * @return
     */
    protected String[] getSplitInput(Element currentElement, String regex){
        String input = currentElement.getTextContent();
        String[] inputSplitted = input.split(",");
        return inputSplitted;
    }

    /**
     * Returns nested NodeList
     * @param parent
     * @param children
     * @param element
     * @return
     */
    protected NodeList getNestedNodeList(String parent, String children, Element element){
        Element parentElement = extractNodeElementFromParent(parent, element);
        return getNodeList(children, parentElement);
    }

    private Element convertNodetoElement(Node node){
        if(node.getNodeType() == Node.ELEMENT_NODE) {
            Element NodeElement = (Element) node;
            return NodeElement;
        }
        else{
            throw new XMLException();
        }
    }

    private Element getRootElement(){
        return myRootElement;
    }

    private Document documentSetup(File file){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document document = null;
        try {
            builder = factory.newDocumentBuilder();
            document = builder.parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new ParserException(e.getMessage());
        }
        document.getDocumentElement().normalize();
        return document;
    }

    private Element extractRoot(){
        Element root = myDocument.getDocumentElement();
        return root;
    }

    private Node getChildNode(String tag, Element parentElement){
        return getNodeList(tag, parentElement).item(0);
    }
}