package ooga.parser.components;

import ooga.parser.helperObjects.Control;
import ooga.parser.helperObjects.Interaction;
import ooga.parser.helperObjects.Character;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Job of this class is to parse and extract character info. Uses the DOM Parser API.
 * @author Alex Xu
 */
public class CharacterInfoParser extends AbstractParser {
    public static final String RESOURCES_PATH = "ooga.parser.properties.characterInfoProperties";
    public static final String XSD = "src/ooga/parser/validators/xsdSchema/character.xsd";

    public static final String IMAGE = "image";
    public static final String CONTROLS = "controls";
    public static final String SIZE = "size";
    public static final String X = "x";
    public static final String Y = "y";
    public static final String INTERACTIONS = "interactions";
    public static final String INTERACTION = "interaction";
    public static final String WIDTH = "width";
    public static final String HEIGHT = "height";
    public static final String CHARACTER = "character";
    public static final String NAME = "name";
    public static final String CONTROL = "control";
    public static final String REGEX = "regex";

    private List<Character> myCharacters;
    private ResourceBundle myResources;

    public CharacterInfoParser(String filename){
        super(filename, XSD);
        myResources = ResourceBundle.getBundle(RESOURCES_PATH);
        myCharacters = new ArrayList<>();
        extractCharacters();
    }

    /**
     * Returns a list of character objects.
     * @return
     */
    public List<Character> getCharacters(){
        return myCharacters;
    }

    private void extractCharacters(){
        NodeList charactersList = getNodeList(myResources.getString(CHARACTER));
        for(int i = 0; i < charactersList.getLength(); i++){
            Element currentCharacter = extractNodeElementFromList(charactersList, i);
            Character character = extractCharacter(currentCharacter);
            myCharacters.add(character);
        }
    }

    private Character extractCharacter(Element currentCharacter){
        String name = extractElementValue(myResources.getString(NAME), currentCharacter);
        String imageFilePath = extractElementValue(myResources.getString(IMAGE), currentCharacter);
        Element sizeElement = extractNodeElementFromParent(myResources.getString(SIZE), currentCharacter);
        int width = Integer.parseInt(extractElementValue(myResources.getString(WIDTH), sizeElement));
        int height = Integer.parseInt(extractElementValue(myResources.getString(HEIGHT), sizeElement));
        int x = Integer.parseInt(extractElementValue(myResources.getString(X), sizeElement));
        int y = Integer.parseInt(extractElementValue(myResources.getString(Y), sizeElement));
        List<Interaction> interactionsList = extractInteractions(currentCharacter);
        List<Control> controlsList = extractControls(currentCharacter);
        return new Character(name, imageFilePath, x, y, width, height, interactionsList, controlsList);
    }

    private List<Control> extractControls(Element parentElement){
        List<Control> myControls = new ArrayList<>();
        NodeList controlsList = getNestedNodeList(myResources.getString(CONTROLS), myResources.getString(CONTROL), parentElement);
        for(int i = 0; i < controlsList.getLength(); i++){
            Element currentControl = extractNodeElementFromList(controlsList, i);
            Control control = extractControl(currentControl);
            myControls.add(control);
        }
        return myControls;
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

    private Control extractControl(Element currentControl){
        String[] inputSplitted = getSplitInput(currentControl, myResources.getString(REGEX));
        String method = inputSplitted[0].trim();
        String key = inputSplitted[1].trim();
        return new Control(key, method);
    }

    private Interaction extractInteraction(Element currentInteraction){
        String[] inputSplitted = getSplitInput(currentInteraction,myResources.getString(REGEX));
        String objectName = inputSplitted[0].trim();
        String side = inputSplitted[1].trim();
        String interaction = inputSplitted[2].trim();
        return new Interaction(objectName, side, interaction);
    }
}
