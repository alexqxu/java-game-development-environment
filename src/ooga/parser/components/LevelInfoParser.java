package ooga.parser.components;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Job of this class is to parse and extract level info. Uses the DOM Parser API.
 * @author Alex Xu
 */
public class LevelInfoParser extends AbstractParser {
    public static final String BACKGROUND_IMAGE = "backgroundimage";
    public static final String XSD = "src/ooga/parser/validators/xsdSchema/level.xsd";
    public static final String RESOURCES_PATH = "ooga.parser.properties.levelInfoProperties";

    public static final String CELL = "cell";
    public static final String OBJECT = "object";
    public static final String X = "x";
    public static final String Y = "y";

    private Map<Map<Integer, Integer>, String> cellMap;
    private String myBackgroundImage;
    private ResourceBundle myResources;

    public LevelInfoParser(String filename){
        super(filename, XSD);
        myResources = ResourceBundle.getBundle(RESOURCES_PATH);
        cellMap = new HashMap<>();
        extractBackgroundImage();
        extractCells();
    }

    public Map<Map<Integer, Integer>, String> getCellMap(){
        return cellMap;
    }

    public String getBackgroundImage(){
        return myBackgroundImage;
    }

    private void extractCells(){
        NodeList cellList = getNodeList(myResources.getString(CELL));
        for(int i = 0; i < cellList.getLength(); i++){
            Element currentCell = extractNodeElementFromList(cellList, i);
            extractParams(currentCell);
        }
    }

    private void extractParams(Element currentCell){
        String objectType = extractElementValue(myResources.getString(OBJECT), currentCell);
        int xCoord = Integer.parseInt(extractElementValue(myResources.getString(X), currentCell));
        int yCoord = Integer.parseInt(extractElementValue(myResources.getString(Y), currentCell));
        Map<Integer, Integer> coordinateMap = makeCoordinateMap(xCoord, yCoord);
        cellMap.put(coordinateMap, objectType);
    }

    private Map<Integer, Integer> makeCoordinateMap(int xCoord, int yCoord){
        Map<Integer, Integer> coordinateMap = new HashMap<>();
        coordinateMap.put(xCoord, yCoord);
        return coordinateMap;
    }

    private void extractBackgroundImage() {
        myBackgroundImage = extractElementValue(myResources.getString(BACKGROUND_IMAGE));
    }
}