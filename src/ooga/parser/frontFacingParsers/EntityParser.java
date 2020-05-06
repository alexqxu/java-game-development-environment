package ooga.parser.frontFacingParsers;

import ooga.controller.ParameterController;
import ooga.parser.components.*;
import ooga.parser.helperObjects.*;
import ooga.parser.helperObjects.Character;
import ooga.parser.utilities.GridLocationInterpreter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class uses composition to extract information required for creating entities / parameter controllers.
 * @author Alex Xu
 */

public class EntityParser {
    public static final String CHARACTER_CONFIG_SUFFIX = "characterConfig.xml";
    public static final String OBJECTS_CONFIG_SUFFIX = "objects.xml";
    public static final String LEVEL_CONFIG = "level_";
    public static final String PLAYER_PREFIX = "Player_";
    public static final String OBJECT_PREFIX = "Object_";
    public static final String CHARACTER_TYPE = "Character";
    public static final String XML_EXTENSION = ".xml";

    private CharacterInfoParser characterInfoParser;
    private LevelInfoParser levelInfoParser;
    private ObjectsInfoParser objectsInfoParser;

    private Map<String, ParameterController> parsedDataMap;
    private String myFolderPath;

    public EntityParser(String folderPath){
        myFolderPath = folderPath;
        characterInfoParser = new CharacterInfoParser(folderPath + CHARACTER_CONFIG_SUFFIX);
        objectsInfoParser = new ObjectsInfoParser(folderPath + OBJECTS_CONFIG_SUFFIX);
        parsedDataMap = new HashMap<>();
        setLevel(1, folderPath);
    }

    /**
     *
     * @return the map that holds the name of the utility and its parameterController
     */
    public Map<String, ParameterController> getParameters() {
        return parsedDataMap;
    }

    public void setLevel(int level, String folderPath){
        levelInfoParser = new LevelInfoParser(folderPath+LEVEL_CONFIG+level+XML_EXTENSION);
        addParams();
    }

    /**
     *  add the name of the entity as a key and a parameterController that has all the parameters the entities
     *  Added the following code to show how the data flow.
     */
    private void addParams() {
        addPlayers();
        addObjects();
    }

    private void addPlayers(){
        List<Character> characterList = characterInfoParser.getCharacters();

        int counter = 1;
        for(Character character: characterList){
            String name = PLAYER_PREFIX + counter;

            ParameterController parameterController = extractPlayerParameterController(character, name, counter);

            parameterController.setName(name);
            parameterController.setType(CHARACTER_TYPE);

            //Adding in the Interactions.
            List<Interaction> interactionList = character.getInteractions();
            for(Interaction interaction: interactionList){
                String object = interaction.getMyObject();
                String side = interaction.getSide();
                String action = interaction.getInteractionName();
                parameterController.setInteractions(object, side, action);
            }

            //Adding the controls.
            List<Control> controlList = character.getControls();
            for(Control control: controlList){
                parameterController.setControllers(control.getKey(), control.getMethod());

            }



            parsedDataMap.put(name, parameterController);
            counter++;
        }
    }

    private void addObjects(){
        ObjectBuffer objectsList = objectsInfoParser.getObjects();
        Map<Map<Integer, Integer>, String> levelConfig = levelInfoParser.getCellMap();

        int i = 1;
        for(Map<Integer, Integer> key : levelConfig.keySet()){
            String objectName = levelConfig.get(key);
            GameObject gameObject = objectsList.get(objectName);
            String name=OBJECT_PREFIX + i;

            ParameterController parameterController = extractObjectParameterController(gameObject, key, name);

            parsedDataMap.put(name, parameterController);
            i++;
        }
    }

    private ParameterController extractPlayerParameterController(Character character, String name, int counter){
        ParameterController parameterController=new ParameterController();
        parameterController.setImage(myFolderPath + character.getImagePath());
        addCharacterDimensions(character, parameterController);
        setName(name, parameterController, counter);
        setCharacterInteractions(character, parameterController);
        setControls(character, parameterController);
        return parameterController;
    }

    private void addCharacterDimensions(Character character, ParameterController pc){
        pc.setCoordinate(GridLocationInterpreter.projectGridToPixel(character.getX()), GridLocationInterpreter.projectGridToPixel(character.getY()));
        pc.setWidth(GridLocationInterpreter.projectGridToPixel(character.getWidth()));
        pc.setHeight(GridLocationInterpreter.projectGridToPixel(character.getHeight()));
    }

    private void setName(String name, ParameterController pc, int counter){
        pc.setName(name);
        pc.setType(CHARACTER_TYPE);
    }

    private void setCharacterInteractions(Character character, ParameterController parameterController){
        List<Interaction> interactionList = character.getInteractions();
        for(Interaction interaction: interactionList){
            String object = interaction.getMyObject();
            String side = interaction.getSide();
            String action = interaction.getInteractionName();
            parameterController.setInteractions(object, side, action);
        }
    }

    private void setControls(Character character, ParameterController parameterController){
        List<Control> controlList = character.getControls();
        for(Control control: controlList){
            parameterController.setControllers(control.getKey(), control.getMethod());
        }
    }

    private void setObjectDimensions(ParameterController parameterController, Map<Integer, Integer> key){
        for(int x : key.keySet()){
            int xLoc = x;
            int yLoc = key.get(x);
            parameterController.setCoordinate(GridLocationInterpreter.projectGridToPixel(xLoc), GridLocationInterpreter.projectGridToPixel(yLoc));
        }
    }

    private void extractObjectInteractions(ParameterController parameterController, GameObject gameObject){
        //Adding in the Interactions.
        List<Interaction> interactionList = gameObject.getInteractions();
        for(Interaction interaction: interactionList){
            String object = interaction.getMyObject();
            String side = interaction.getSide();
            String action = interaction.getInteractionName();
            parameterController.setInteractions(object, side, action);
        }
    }

    private void extractObjectMovements(ParameterController parameterController, GameObject gameObject){
        //Adding the movements
        List<Movement> movementList = gameObject.getMovements();
        for(Movement movement: movementList){
            String movementName = movement.getName();
            int movementAmount = movement.getDistance();
            parameterController.addPredefinedMotion(movementName, movementAmount);
        }
    }

    private ParameterController extractObjectParameterController(GameObject gameObject, Map<Integer, Integer> key, String name){
        ParameterController parameterController=new ParameterController();
        setObjectDimensions(parameterController, key);
        parameterController.setImage(myFolderPath + gameObject.getImagePath());
        parameterController.setHeight(GridLocationInterpreter.projectGridToPixel(gameObject.getHeight()));
        parameterController.setWidth(GridLocationInterpreter.projectGridToPixel(gameObject.getWidth()));
        extractObjectInteractions(parameterController, gameObject);
        extractObjectMovements(parameterController, gameObject);
        String type= gameObject.getName();
        parameterController.setName(name);
        parameterController.setType(type);
        return parameterController;
    }
}
