package ooga.parser.components;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Job of this class is to parse and extract general info. Uses the DOM Parser API.
 * @author Alex Xu
 */
public class GeneralInfoParser extends AbstractParser {
    public static final String RESOURCES_PATH = "ooga.parser.properties.generalInfoProperties";
    public static final String XSD = "src/ooga/parser/validators/xsdSchema/generalConfig.xsd";
    public static final String GENERAL_CONFIG_SUFFIX = "generalConfig.xml";
    public static final String BOUNDARY = "boundary";
    public static final String BOUNDARIES = "boundaries";
    public static final String SCORE_THRESHOLD = "scorethreshold";

    private List<String> myBoundaries;
    private ResourceBundle myResources;
    private int myThreshold;

    public GeneralInfoParser(String filename){
        super(filename+GENERAL_CONFIG_SUFFIX, XSD);
        myResources = ResourceBundle.getBundle(RESOURCES_PATH);
        myBoundaries = new ArrayList<>();
        extractBoundaries();
    }

    public List<String> getBoundaries(){
        return myBoundaries;
    }

    public int getThreshold(){
        return myThreshold;
    }

    private void extractBoundaries(){
        Element boundaryNode = extractNodeElementFromRoot(myResources.getString(BOUNDARIES));
        NodeList boundariesList = getNodeList(myResources.getString(BOUNDARY), boundaryNode);

        for(int i = 0; i<boundariesList.getLength(); i++){
            Element currentBoundary = extractNodeElementFromList(boundariesList,i);
            myBoundaries.add(currentBoundary.getNodeValue());
        }
    }

    private void extractThreshold(){
        myThreshold = Integer.parseInt(extractElementValue(myResources.getString(SCORE_THRESHOLD)));
    }
}