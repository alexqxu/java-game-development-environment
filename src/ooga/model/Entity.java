
package ooga.model;

import api.EntityAPI;
import api.InternalPlayerAPI;
import java.util.Collections;

import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;
import ooga.controller.ParameterController;
import ooga.engine.helperObjects.Trigger;
import ooga.utilities.*;
import ooga.utilities.exception.EntityBoundsException;
import ooga.utilities.exception.ReflectionException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * This class implements the EntityAPI and has all the methods and parameters that a general entity needs
 */
public abstract class Entity implements EntityAPI, InternalPlayerAPI {

    private boolean isActive;
    private boolean isFalling;
    private ParameterController myPC;
    private SimpleObjectProperty<Coordinate> myDynamicCoordinate;
    private Force force;
    private static final int INITIAL_VALUE=0;
    private Trigger currentAction;
    protected SimpleObjectProperty<EntityGameStatus> myGameStatus;


    private Velocity velocity;



    private Acceleration acceleration;

    /**
     *
     * @param pc parameter controller that has all the values/parameters that a specific entity needs
     */
    public Entity(ParameterController pc){
        myPC = pc;
        isFalling = true;
        myDynamicCoordinate = new SimpleObjectProperty<>(pc.getCoordinate());
        force = new Force(INITIAL_VALUE,INITIAL_VALUE);
        velocity=new Velocity(INITIAL_VALUE,INITIAL_VALUE);
        acceleration=new Acceleration(INITIAL_VALUE,INITIAL_VALUE);
    }

    /**
     *
     * @return SimpleObjectProperty of the entity that will be used for binding
     */
    public SimpleObjectProperty<Entity> getSimpleEntityProperty(){
        return new SimpleObjectProperty<>(this);
    }

    /**
     * Updates the coordinate of the entity to coordinates + delta
     * @param delta the change in coordinates of the entity
     *
     */
    public void move(Delta delta){
       int currentX = myPC.getCoordinate().getX();
       int currentY = myPC.getCoordinate().getY();
       myPC.setCoordinate(currentX + delta.getMoveX(), currentY + delta.getMoveY());
    }

    /**
     *
     * @return boolean value checks if the entity can be shown on the window.
     */
    @Deprecated
    public boolean isActive(){
        return isActive;
    }

    /**
     *
     * @param score the new score of the entity
     */
    //public void setScore(int score){
    //    myScoreInfo.setValue(new WinnerProfile(getName(), score));
    //}

    /**
     *
     * @return the current score of the entity
     */
    //public int getScore(){ return myScoreInfo.getValue().getScore();}

    //TODO improve exception handling

    /**
     *
     * @return entity's image pixel width and height
     */
    public Bounds getBounds(){
        return new Rectangle(myPC.getCoordinate().getX(), myPC.getCoordinate().getY(), myPC.getWidth(), myPC.getHeight()).getLayoutBounds();
    }

    /**
     * executes the list of movements to the entity
     */
    public void executeMovementScript(Map<String, Integer> movements){
        for(String move : movements.keySet()){
            try{
                Class.forName(this.getClass().getName()).getMethod(move).invoke(this, movements.get(move));
            }
            catch(Exception e){
                throw new ReflectionException(e.getMessage());
            }
        }
    }

    /**
     *
     * @param amount the amount of pixels to move to the left
     */
    public void moveLeft(int amount) {
        int currentX = myPC.getCoordinate().getX();
        int currentY = myPC.getCoordinate().getY();
        myPC.setCoordinate(currentX - amount, currentY);
    }

    /**
     *
     * @param amount the amount of pixels to move to the right
     */
    public void moveRight(int amount) {
        int currentX = myPC.getCoordinate().getX();
        int currentY = myPC.getCoordinate().getY();
        myPC.setCoordinate(currentX + amount, currentY);
    }

    /**
     *
     * @param amount the amount of pixels to move to the up
     */
    public void moveUp(int amount) {
        int currentX = myPC.getCoordinate().getX();
        int currentY = myPC.getCoordinate().getY();
        myPC.setCoordinate(currentX, currentY + amount);
    }

    /**
     *
     * @param angle the angle to for the character to move where zero degrees is defined as along the plane
     *              which the player's character is facing
     * @param amount the amount of pixels to move the player's character at the specified angle
     */
    public void move(double angle, int amount) {
        int currentX = myPC.getCoordinate().getX();
        int currentY = myPC.getCoordinate().getY();
        int deltaX = (int) (Math.cos(angle) * amount);
        int deltaY = (int) (Math.sin(angle) * angle);
        myPC.setCoordinate(currentX + deltaX, currentY + deltaY);
    }

    @Override
    public void setLives(int health) {

    }

    @Override
    public void setScore(int score) {

    }

    /**
     * Set the entity's health to a specified value
     * @param health the new health of the entity
     */
    //public void setLives(int health) {
    //    myLives.setValue(health);
    //}

    /**
     *
     * @return
     */
    public Map<String, String> getKeyBindings(){
       return Collections.unmodifiableMap(myPC.getControllerMethod());
    }

    /**
     * Set whether gravity should be enabled for the entity
     * @param falling true the entity should be under the influence of gravity; false otherwise
     */
    public void setFalling(boolean falling){
       isFalling = falling;
    }

    public boolean checkFalling(){
        return isFalling;
    }

    public String getName(){
        return myPC.getName();
    }

    //public int getLives(){
    //    return myLives.getValue();
    //}

    /**
     *
     * @param entityName the name of the Entity that this entity is interacting with
     * @param collisionType the type of collision that occurred with the other Entity
     * @return a key mapping to a method call in a resource file defining what this Entity should do as a result
     * of the collision
     */
    public List<String> getCollisionAction(String entityName, String collisionType){
        return myPC.getInteractionMethod(entityName, collisionType);
    }

    public Coordinate getCoordinate(){
        return myPC.getCoordinate();
    }

    public SimpleObjectProperty<Coordinate> getDynamicEntityCoordinate(){
        return myDynamicCoordinate;
    }

    //public SimpleObjectProperty<WinnerProfile> getScoreInfo(){ return myScoreInfo; }

    //public SimpleObjectProperty<Integer> getLivesInfo(){ return myLives; }

    public void setCoordinate(int x, int y){
        myPC.setCoordinate(x,y);
        myDynamicCoordinate.setValue(new Coordinate(myPC.getCoordinate().getX(), myPC.getCoordinate().getY()));
    }

    @Deprecated
    public ParameterController getParameterController() {
        return myPC;
    }

    public void setVelocity(double xVelocity, double yVelocity) {
        this.velocity = new Velocity(xVelocity, yVelocity);
    }

    public void setAcceleration(double xAcceleration, double yAcceleration) {
        this.acceleration =new Acceleration(xAcceleration, yAcceleration);
    }

    /**
     *
     * @return the force that is exerted to this entity
     */
    public Force getForce(){
        return force;
    }

    /**
     *
     * @param x component of the force
     * @param y component of the force
     */
    public void setForce(double x, double y){
        force.setxForce(x);
        force.setyForce(y);
    }

    /**
     *
     * @return the velocity of this entity
     */
    public Velocity getVelocity() {
        return velocity;
    }

    public Acceleration getAcceleration() {
        return acceleration;
    }

    //TODO: This method was implemented as a fix -Alex
    public boolean equals(Entity entity){
        return myPC.getName().equals(entity.getParameterController().getName());
    }

    public SimpleObjectProperty<EntityGameStatus> getEntityGameStatus(){
        return myGameStatus;
    }

    public boolean interactionObjectDefined(String interactedObject){
        return myPC.getInteractionMap().containsKey(interactedObject);
    }

    public boolean overallInteractionDefined(String interactedObject, String collisionType, String action){
        return myPC.getInteractionMethod(interactedObject, collisionType).contains(action);
    }

    public void setStatus(String newStatus){
        EntityGameStatus oldStatus = myGameStatus.getValue();
        myGameStatus.setValue(new EntityGameStatus(getName(), newStatus, oldStatus.getLives(), oldStatus.getScore()));
    }

}
