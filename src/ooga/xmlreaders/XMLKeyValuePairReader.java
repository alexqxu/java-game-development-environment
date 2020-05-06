package ooga.xmlreaders;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javafx.util.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Object for reading key-value pairs from an XML file
 */
public class

XMLKeyValuePairReader {

    Map<String, String> values;

    /**
     * Read the key-value pairs from an XML file
     *
     * @param path The path to the file
     */
    public void read(String path) throws XMLReaderException {
      values = null;
      Node node = getFirstNode(path);
      values = buildMap(node);
    }

    /**
     * Build the map from the XML node
     *
     * @param node The first node in the XML to read
     * @return The map of the key-value pairs
     */
    private Map<String, String> buildMap(Node node) {
      Map<String, String> values = new HashMap<>();
      String value;
      String key;
      while (node != null) {
        if (node.getAttributes() != null) {
          key = node.getNodeName();
          value = node.getTextContent().strip();
          values.put(key, value);
        }
        node = node.getNextSibling();
      }
      return values;
    }

    /**
     * Find the first node in the given XML
     *
     * @param path The path to the XML file
     * @return THe first node in the XML
     * @throws XMLReaderException The file was unable to be parsed
     */
    private Node getFirstNode(String path) throws XMLReaderException {
      Document doc = createDocument(path);
      doc.getDocumentElement().normalize();
      return doc.getDocumentElement().getFirstChild();
    }

  private Document createDocument(String path) {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db;
    try {
      db = dbf.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      throw new XMLReaderException("The XML file was unable to be parsed", e);
    }
    Document doc;
    try {
      doc = db.parse(path);
    } catch (SAXException | IOException e) {
      throw new XMLReaderException("The XML file was unable to be parsed", e);
    }
    return doc;
  }

  /**
   *
   */
  public NodeList getElementsByTagName(String path, String tagName){
    Document doc = createDocument(path);
    return doc.getElementsByTagName(tagName);
  }

  public Element getElements(String path)
      throws ParserConfigurationException, IOException, SAXException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse(new InputSource(new ByteArrayInputStream(path.getBytes("UTF-8"))));
    Element rootElement = document.getDocumentElement();
    return rootElement;
  }

  /**
     * Retrieve and interpret the value of a key as a Double
     *
     * @param key The key's value to get
     * @return The value as a Double
     * @throws XMLReaderException The key cannot be retrieved as a Double
     */
    public double getDouble(String key) throws XMLReaderException {
      try {
        return Double.parseDouble(get(key));
      } catch (XMLReaderException | NumberFormatException e) {
        throw new XMLReaderException(
            "Value for '" + key + "' is not able to be interpreted as a Double", e);
      }
    }



    /**
     * Retrieve and interpret the value of a key as an Int
     *
     * @param key The key's value to get
     * @return The valye as an Int
     * @throws XMLReaderException The key cannot be retrieved as an Int
     */
    public int getInt(String key) throws XMLReaderException {
      try {
        return Integer.parseInt(get(key));
      } catch (XMLReaderException | NumberFormatException e) {
        throw new XMLReaderException(
            "Value for '" + key + "' is not able to be interpreted as a Integer", e);
      }
    }

    /**
     * Retrieve and interpret the value of a key as a Float
     *
     * @param key The key's value to get
     * @return The value as a Float
     * @throws XMLReaderException The key cannot be retrieved as a Float
     */
    public float getFloat(String key) throws XMLReaderException {
      try {
        return Float.parseFloat(get(key));
      } catch (XMLReaderException | NumberFormatException e) {
        throw new XMLReaderException(
            "Value for '" + key + "' is not able to be interpreted as a Float", e);
      }
    }

    /**
     * Retrieve and interpret the value of a key as a Boolean
     *
     * @param key The key's value to get
     * @return The value as a Boolean
     * @throws XMLReaderException The key cannot be retrieved as a Boolean
     */
    public boolean getBoolean(String key) throws XMLReaderException {
      try {
        return Boolean.parseBoolean(get(key));
      } catch (XMLReaderException e) {
        throw new XMLReaderException(
            "Value for '" + key + "' is not able to be interpreted as a Boolean", e);
      }
    }

    /**
     * Retrieve and interpret the value of a key as a String
     *
     * @param key The key's value to get
     * @return The value as a String
     * @throws XMLReaderException The key cannot be retrieved as a String
     */
    public String getString(String key) throws XMLReaderException {
      return get(key);
    }

    /**
     * Retrieve and interpret the value of a key as a String
     *
     * @param key The key's value to get
     * @return The value as a String
     * @throws XMLReaderException The key cannot be retrieved as a String
     */
    public String getStringNoWhitespace(String key) throws XMLReaderException {
      return get(key).replaceAll("\\s", "");
    }

    /**
     * Retrieve and interpret the value of a key as a Double, or return the default if it fails
     *
     * @param key          The key's value to get
     * @param defaultValue If no valid key-value pair is found return this value
     * @return Either the value of the key-value pair or the default value
     */
    public double getDoubleOrDefault(String key, double defaultValue) {
      try {
        return getDouble(key);
      } catch (XMLReaderException e) {
        // Throw exception away and return default value
        return defaultValue;
      }
    }

    /**
     * Retrieve and interpret the value of a key as an int, or return the default if it fails
     *
     * @param key          The key's value to get
     * @param defaultValue If no valid key-value pair is found return this value
     * @return Either the value of the key-value pair or the default value
     */
    public int getIntOrDefault(String key, int defaultValue) {
      try {
        return getInt(key);
      } catch (XMLReaderException e) {
        // Throw exception away and return default value
        return defaultValue;
      }
    }

    /**
     * Retrieve and interpret the value of a key as a Float, or return the default if it fails
     *
     * @param key          The key's value to get
     * @param defaultValue If no valid key-value pair is found return this value
     * @return Either the value of the key-value pair or the default value
     */
    public float getFloatOrDefault(String key, float defaultValue) {
      try {
        return getFloat(key);
      } catch (XMLReaderException e) {
        // Throw exception away and return default value
        return defaultValue;
      }
    }

    /**
     * Retrieve and interpret the value of a key as a Double, or return the default if it fails
     *
     * @param key          The key's value to get
     * @param defaultValue If no valid key-value pair is found return this value
     * @return Either the value of the key-value pair or the default value
     */
    public boolean getBooleanOrDefault(String key, boolean defaultValue) {
      try {
        return getBoolean(key);
      } catch (XMLReaderException e) {
        // Throw exception away and return default value
        return defaultValue;
      }
    }

    /**
     * Retrieve and interpret the value of a key as a Double, or return the default if it fails
     *
     * @param key          The key's value to get
     * @param defaultValue If no valid key-value pair is found return this value
     * @return Either the value of the key-value pair or the default value
     */
    public String getStringOrDefault(String key, String defaultValue) {
      try {
        return getString(key);
      } catch (XMLReaderException e) {
        // Throw exception away and return default value
        return defaultValue;
      }
    }

    /**
     * Get the value from a key
     *
     * @param key The key
     * @return The value
     * @throws XMLReaderException The key was not found in the XML file
     */
    private String get(String key) throws XMLReaderException {
      checkIfFileWasRead();
      String ret = values.get(key);
      if (ret == null) {
        throw new XMLReaderException("Key '" + key + "' is not a valid key in the XML file");
      }
      return ret;
    }

    /**
     * Get a set of all keys in the XML
     *
     * @return Set of keys
     */
    public Set<String> getKeys() {
      checkIfFileWasRead();
      return values.keySet();
    }

    /**
     * Checks if a file was read
     *
     * @throws XMLReaderException No file has been successfully read
     */
    private void checkIfFileWasRead() throws XMLReaderException {
      if (values == null) {
        throw new XMLReaderException("No XML file has been successfully read");
      }
    }

    /**
     * Represents errors in reading XML information
     */
    private static class XMLReaderException extends RuntimeException {

      /**
       * Create a new XMLReaderException
       *
       * @param message The message to associate with the error
       * @param e       The causing exception
       */
      XMLReaderException(String message, Throwable e) {
        super(message, e);
      }

      /**
       * Create a new XMLReaderException
       *
       * @param message The message to associate with the error
       */
      XMLReaderException(String message) {
        super(message);
      }
    }
    //====================================Ryan's code to review==================
  public Collection<Element> getParentNodes(String path){
    List<Element> parentNodes = new ArrayList<>();
    Node node = getFirstNode(path);
    //TODO: REFACTOR
    while( node.getNextSibling()!=null ){
      node = node.getNextSibling();
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element childElement = (Element) node;
        parentNodes.add(childElement);
      }
    }
    return Collections.unmodifiableList(parentNodes);
  }

  public Collection<Pair> getChildrenNodePairs(String path, Element parentNode){
      List<Pair<String, String>> childrenNodes = new ArrayList<>();
    Node node = parentNode.getFirstChild();
    //TODO:REFACTOR
    while( node.getNextSibling()!=null ){
      node = node.getNextSibling();
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element childElement = (Element) node;
        childrenNodes.add(new Pair<String, String>(childElement.getNodeName(), childElement.getAttribute("name")));
      }
    }
    return Collections.unmodifiableList(childrenNodes);
  }

  private Node getParentNodeByTage(String path, String parentNodeName) {
    Document doc = createDocument(path);
    Node parentNode = doc.getDocumentElement().getElementsByTagName(parentNodeName).item(0);
    return parentNode;
  }

  public Collection<Element> getChildrenNodes(Element parentNode){
    List<Element> childrenNodes = new ArrayList<>();
    Node node = parentNode.getFirstChild();
    //TODO:REFACTOR
    while( node.getNextSibling()!=null ){
      node = node.getNextSibling();
      if (node.getNodeType() == Node.ELEMENT_NODE) {
        Element childElement = (Element) node;
        childrenNodes.add(childElement);
      }
    }
    return Collections.unmodifiableList(childrenNodes);
  }

  public Pair<String, String> getNodePair(Element element){
    return new Pair(element.getNodeName(), element.getAttribute("name"));
  }
}

