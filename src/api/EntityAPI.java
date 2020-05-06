package api;

import ooga.utilities.Coordinate;
import ooga.utilities.Delta;

import java.util.List;
import java.util.Map;

/**
 * Provides functionality to support the movement of game entities in the frame
 */
public interface EntityAPI {


    /**
     * Move the entity along the y-axis and x-axis by a specified amount
     * @param delta the change in coordinate
     */
    public void move(Delta delta);



    /**
     * check if the entity should be present in the view
     * @return bolean
     */
    @Deprecated
    public boolean isActive();


    /**
     * Execute a series of pre-defined movements
     * @param movements a series of movement method calls to execute to move the entity, where the key is the method to be called
     *                  and the value is amount of pixels that the entity should move
     */
    public void executeMovementScript(Map<String,Integer> movements);
}
