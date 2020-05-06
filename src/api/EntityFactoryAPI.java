package api;

import ooga.controller.ParameterController;

/**
 * Provides functionality to produce new instances of various game entities using a factory pattern
 */
public interface EntityFactoryAPI {

    /**
     * Return a new instance of a specified game entity
     * @param entityName the name of the desired entity
     * @param pc a ParameterController that has all the utilities the class needs
     * @return a new instance of a game entity with the specified name
     */
    public EntityAPI getEntity(String entityName, ParameterController pc);
}
