package ooga.model.backEndEntities;
import api.EntityFactoryAPI;
import ooga.controller.ParameterController;
import ooga.model.Entity;
import ooga.utilities.exception.EntityCreationError;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

/**
 * This class implements the EntityFactoryAPI and constructs game Entities using the name of the Entity given
 * getEntity method
 */
public class EntityFactory implements EntityFactoryAPI {
    private static final String FILE_PATH="resources.dataProperties.FactoryProperties";
    private ResourceBundle textForFactory = java.util.ResourceBundle.getBundle(FILE_PATH);
    private static final String CLASSPATH = "EntityFactoryPath";
    private static final String EXTENSION = "Entity";
    private static final int FIRST_PARAM = 0;
    private static final String BACKEND_ENTITY_ERROR = "BackEndEntityFactoryError";

    @Override
    public Entity getEntity(String name, ParameterController par) {

        Constructor[] constructors;

        try {
            constructors = Class.forName(textForFactory.getString(CLASSPATH) + name + EXTENSION).getDeclaredConstructors();
            return (Entity) constructors[FIRST_PARAM].newInstance(par);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new EntityCreationError(textForFactory.getString(BACKEND_ENTITY_ERROR)+name);
        }
    }

}
