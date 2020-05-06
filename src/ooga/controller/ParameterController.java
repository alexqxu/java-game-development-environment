package ooga.controller;

import ooga.engine.helperObjects.PreDefinedMotionHandler;
import ooga.utilities.Coordinate;

import java.util.*;

public class ParameterController {

    private String myImageDir;
    private Map<String, InteractionBinder> myInteractionMap;
    private Map<String, String> myControllerMethod;
    private Map<String, Object> myPhysicsValue;
    private Coordinate coordinate;
    private int myHeight;
    private int myWidth;
    private String myName;
    private String myType;
    private PreDefinedMotionHandler preDefinedMotionHandler;


    /**
     * This class will have all the parameters that a specific entity needs
     */
    public ParameterController(){
        myInteractionMap = new HashMap<>();
        myControllerMethod = new HashMap<>();
        myPhysicsValue = new HashMap<>();
        preDefinedMotionHandler=new PreDefinedMotionHandler();

    }

    /**
     *
     * @param imageDir address of the image
     * @return the image address
     */
    public void setImage(String imageDir){
        myImageDir = imageDir;
    }

    public void setInteractions(String objectType, String interactionType, String methodCall){
        myInteractionMap.putIfAbsent(objectType, new InteractionBinder());
        myInteractionMap.get(objectType).addBinding(interactionType, methodCall);
    }

    /**
     * Bind a keypress to a method call
     * @param key the keypress of interest
     * @param method the method to be called when the keypress occurs
     */
    public void setControllers(String key, String method){
        myControllerMethod.put(key, method);
    }

    public void setPhysicsValue(String physicsType, Object value) {
        myPhysicsValue.put(physicsType, value);
    }


    public void setCoordinate(int x, int y){
        coordinate = new Coordinate(x,y);
    }

    public void setHeight(int value){
        myHeight = value;
    };

    public void setWidth(int value){
        myWidth = value;
    };

    public void setType(String value){
        myType = value;
    }

    public void setName(String name){
        myName = name;
    }

    public Map<String, InteractionBinder> getInteractionMap() {
        return myInteractionMap;
    }

    public List<String> getInteractionMethod(String entityName, String collisionType){
        return myInteractionMap.get(entityName).getMethods(collisionType);
    }

    public Map<String, String> getControllerMethod() {
        return myControllerMethod;
    }

    public Map<String, Object> getMyPhysicsValue() {
        return myPhysicsValue;
    }

    public Coordinate getCoordinate(){
        return coordinate;
    }

    public String getImage(){
        return myImageDir;
    }

    public String getName(){
        return myName;
    }

    public String getType(){
        return myType;
    }

    public PreDefinedMotionHandler getPreDefinedMotionHandler() {
        return preDefinedMotionHandler;
    }

    public void addPredefinedMotion(String name, int amount){
        preDefinedMotionHandler.addAction(name, amount);
    }

    public int getWidth(){
        return myWidth;
    }
    public int getHeight(){
        return myHeight;
    }
}
