package ooga.model;

import ooga.controller.InteractionBinder;
import ooga.controller.ParameterController;

import java.util.HashMap;
import java.util.Map;

public class HardCodedEntityParser {
    private Map<String, InteractionBinder> myInteractionMap;
    private Map<String, String> myControllerMethod;


    public HardCodedEntityParser(){


    }

    public void addInteractionMap(ParameterController pc){
        //myInteractionMap = pc.getInteractionMap();
        myInteractionMap.put("Object_1", new InteractionBinder());
        myInteractionMap.get("Object_1").addBinding("top","loseLife");
        myInteractionMap.get("Object_1").addBinding("bottom","deleteEntity");
        myInteractionMap.get("Object_1").addBinding("left","loseLife");
        myInteractionMap.get("Object_1").addBinding("right","loseLife");
    }

    public void addControllerMap(ParameterController pc){
        if (pc.getName().equals("Player_1")) {
            myControllerMethod = pc.getControllerMethod();
            myControllerMethod.put("LEFT", "moveLeft");
            myControllerMethod.put("RIGHT", "moveRight");
            myControllerMethod.put("UP", "moveUp");
            myControllerMethod.put("DOWN", "moveDown");
        }
    }

    public void setName(Entity entity){

    }
}
