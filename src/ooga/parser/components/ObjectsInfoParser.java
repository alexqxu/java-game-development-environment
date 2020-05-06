package ooga.parser.components;

import ooga.parser.helperObjects.GameObject;
import ooga.parser.helperObjects.Interaction;
import ooga.parser.helperObjects.Movement;
import ooga.parser.helperObjects.ObjectBuffer;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Job of this class is to parse and extract object info. Uses the DOM Parser API.
 * @author Alex Xu
 */
public class ObjectsInfoParser extends AbstractParser {
    public static final String XSD = "src/ooga/parser/validators/xsdSchema/objects.xsd";
    public static final String RESOURCES_PATH = "ooga.parser.properties.objectsInfoProperties";

    public static final String OBJECT = "object";
    public static final String NAME = "name";
    public static final String IMAGE_PATH = "imagePath";
    public static final String SIZE = "size";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String INTERACTIONS = "interactions";
    public static final String INTERACTION = "interaction";
    public static final String MOVEMENTS = "movements";
    public static final String MOVEMENT = "movement";
    public static final String REGEX = "regex";

    private ObjectBuffer myObjects;
    private ResourceBundle myResources;

    public ObjectsInfoParser(String filename){
        super(filename, XSD);
        myResources = ResourceBundle.getBundle(RESOURCES_PATH);
        myObjects = new ObjectBuffer();
        extractObjects();
    }

    public ObjectBuffer getObjects(){
        return myObjects;
    }

    private void extractObjects(){
        NodeList objectsList = getNodeList(myResources.getString(OBJECT));
        for(int i = 0; i < objectsList.getLength(); i++){
            Element currentObject = extractNodeElementFromList(objectsList, i);
            GameObject gameObject = extractObject(currentObject);
            myObjects.add(gameObject);
        }
    }

    private GameObject extractObject(Element currentObject){
        String name = extractElementValue(myResources.getString(NAME), currentObject);
        String imagePath = extractElementValue(myResources.getString(IMAGE_PATH), currentObject);

        Element sizeElement = extractNodeElementFromParent(myResources.getString(SIZE), currentObject);
        int width = Integer.parseInt(extractElementValue(myResources.getString(WIDTH), sizeElement));
        int height = Integer.parseInt(extractElementValue(myResources.getString(HEIGHT), sizeElement));

        List<Interaction> interactionsList = extractInteractions(currentObject);
        List<Movement> movementsList = extractMovements(currentObject);

        return new GameObject(name, imagePath, width, height, interactionsList, movementsList);
    }

    private List<Interaction> extractInteractions(Element parentElement) {
        List<Interaction> myInteractions = new ArrayList<>();
        NodeList interactionsList = getNestedNodeList(myResources.getString(INTERACTIONS), myResources.getString(INTERACTION), parentElement);
        for(int i = 0; i < interactionsList.getLength(); i++){
            Element currentInteraction = extractNodeElementFromList(interactionsList, i);
            Interaction interaction = extractInteraction(currentInteraction);
            myInteractions.add(interaction);
        }
        return myInteractions;
    }

    private List<Movement> extractMovements(Element parentElement){
        List<Movement> myMovements = new ArrayList<>();
        NodeList movementsList = getNestedNodeList(myResources.getString(MOVEMENTS), myResources.getString(MOVEMENT), parentElement);
        for(int i = 0; i<movementsList.getLength(); i++){
            Element currentMovement = extractNodeElementFromList(movementsList, i);
            Movement movement = extractMovement(currentMovement);
            myMovements.add(movement);
        }
        return myMovements;
    }

    private Movement extractMovement(Element currentMovement){
        String[] inputSplitted = getSplitInput(currentMovement,myResources.getString(REGEX));
        String movementName = inputSplitted[0].trim();
        int movementAmount = Integer.parseInt(inputSplitted[1].trim());
        return new Movement(movementName, movementAmount);
    }

    private Interaction extractInteraction(Element currentInteraction){
        String[] inputSplitted = getSplitInput(currentInteraction,myResources.getString(REGEX));
        String objectName = inputSplitted[0].trim();
        String side = inputSplitted[1].trim();
        String interaction = inputSplitted[2].trim();
        return new Interaction(objectName, side, interaction);
    }
}
